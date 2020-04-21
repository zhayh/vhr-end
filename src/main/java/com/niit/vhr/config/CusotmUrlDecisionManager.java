package com.niit.vhr.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author : zhayh
 * @date : 2020-4-21 22:10
 * @description : 判断当前用户是否具备这个角色
 */
@Component
public class CusotmUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute attribute : collection) {
            String needRole = attribute.getAttribute();
            if("ROLE_login".equals(needRole)) {
                if(authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请登录");
                }
                return;
            }
            // 获取登录用户的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if(authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("权限不足，请联系管理员");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
