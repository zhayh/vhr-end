package com.niit.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.vhr.model.Hr;
import com.niit.vhr.model.RespBean;
import com.niit.vhr.utils.JwtUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : zhayh
 * @date : 2020-5-4 09:57
 * @description :
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    public LoginFilter(String defaultFilterUrl, AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl(defaultFilterUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String verifyCode = (String) request.getSession().getAttribute("verify_code");
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            // 解析请求的 JSON字符串
            Map<String, String> loginData = new HashMap<>();
            try {
                loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            } catch (IOException e) {
            } finally {
                String code = loginData.get("code");
                checkCode(response, code, verifyCode);
            }
            String username = loginData.get(getUsernameParameter());
            String password = loginData.get(getPasswordParameter());
            username = username == null ? "" : username.trim();
            password = password == null ? "" : password;
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            checkCode(response, request.getParameter("code"), verifyCode);
            return super.attemptAuthentication(request, response);
        }
    }

    public void checkCode(HttpServletResponse resp, String code, String verifyCode) {
        if (StringUtils.isEmpty(code)) {
            throw new AuthenticationServiceException("验证码不能为空！");
        } else if (!code.equalsIgnoreCase(verifyCode)) {
            throw new AuthenticationServiceException("验证码错误！");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 获取登录用户的角色，并组成字符串
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        String authorityStr = authorities.stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // 生成token
        String token = JwtUtils.createToken(authResult.getName(), authorityStr, false);

        // token放到报文头发送
        response.setContentType("application/json;charset=utf-8");
        response.addHeader(JwtUtils.TOKEN_HEADER, JwtUtils.TOKEN_PREFIX + token);

        PrintWriter out = response.getWriter();
        Hr hr = (Hr) authResult.getPrincipal();
        hr.setPassword(null);
        RespBean ok = RespBean.ok("登录成功", hr);
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error("登录失败");
        if (exception instanceof LockedException) {
            respBean.setMsg("账户被锁定，请联系管理员");
        } else if (exception instanceof CredentialsExpiredException) {
            respBean.setMsg("密码过期，请联系管理员");
        } else if (exception instanceof AccountExpiredException) {
            respBean.setMsg("账户过期，请联系管理员");
        } else if (exception instanceof DisabledException) {
            respBean.setMsg("账户被禁用，请联系管理员");
        } else if (exception instanceof BadCredentialsException) {
            respBean.setMsg("用户名或密码输入错误，请重新登录");
        } else if (exception != null && !StringUtils.isEmpty(exception.getMessage())) {
            respBean.setMsg(exception.getMessage());
        }
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }
}
