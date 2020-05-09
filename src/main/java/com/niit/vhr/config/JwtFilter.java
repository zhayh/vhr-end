package com.niit.vhr.config;

import com.niit.vhr.model.Hr;
import com.niit.vhr.service.HrService;
import com.niit.vhr.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-5-6 08:08
 * @description :
 */
@Slf4j
public class JwtFilter extends GenericFilterBean {
    HrService hrService;

    public JwtFilter(HrService hrService) {
        this.hrService = hrService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 从请求头获取 token
        String tokenHeader = request.getHeader(JwtUtils.TOKEN_HEADER);
        if (tokenHeader != null) {
            // 解析token
            String token = tokenHeader.replace(JwtUtils.TOKEN_PREFIX, "");
            try {
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(
                        JwtUtils.getAuthorities(token));
                Hr hr = hrService.getByUsername(JwtUtils.getUsername(token));
                if (hr != null) {
                    hr.setPassword(null);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            hr, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (ExpiredJwtException e) {
                //token过期
                Claims claims = e.getClaims();
                // 大于jwt token过期时间小于redis的存活时间,则允许重新签发一个新的token
                String newToken = JwtUtils.createToken(claims.getSubject(),
                        (String) claims.get(JwtUtils.ROLE_CLAIMS), false);

                response.setContentType("application/json;charset=utf-8");
                response.addHeader(JwtUtils.TOKEN_HEADER, JwtUtils.TOKEN_PREFIX + newToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
