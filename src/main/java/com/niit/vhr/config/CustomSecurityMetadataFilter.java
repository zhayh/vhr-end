package com.niit.vhr.config;

import com.niit.vhr.model.Menu;
import com.niit.vhr.model.Role;
import com.niit.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : zhayh
 * @date : 2020-4-21 21:42
 * @description : 根据用户传来的请求地址，分析出请求需要的角色
 */
@Component
public class CustomSecurityMetadataFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menus = menuService.getAllMenusWithRole();
        for (Menu menu : menus) {
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] names = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    names[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(names);
            }
        }
        // 没有匹配上的所有 url登录之后就能访问
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
