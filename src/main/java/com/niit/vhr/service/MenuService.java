package com.niit.vhr.service;

import com.niit.vhr.mapper.MenuMapper;
import com.niit.vhr.model.Hr;
import com.niit.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zhayh
 * @date : 2020-4-17 20:37
 * @description :
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());

    }

//    @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }
}
