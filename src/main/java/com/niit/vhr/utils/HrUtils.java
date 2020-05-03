package com.niit.vhr.utils;

import com.niit.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author : zhayh
 * @date : 2020-4-28 21:48
 * @description :
 */
public class HrUtils {
    public static Hr getCurrentHr() {
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
