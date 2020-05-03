package com.niit.vhr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * hr
 * @author 
 */

@ApiModel(value = "用户实体类", description = "用户基础及登录信息描述")
public class Hr implements UserDetails {
    @Getter
    @Setter
    @ApiModelProperty(value = "用户id", name = "id", required = true, dataType = "Integer")
    private Integer id;

    @Getter
    @Setter
    @ApiModelProperty(value = "用户姓名")
    private String name;

    @Getter
    @Setter
    @ApiModelProperty(value = "手机号码")
    private String phone;

    @Getter
    @Setter
    @ApiModelProperty(value = "住宅电话")
    private String telephone;

    @Getter
    @Setter
    @ApiModelProperty(value = "联系地址")
    private String address;

    @Setter
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @Setter
    @ApiModelProperty(value = "登录用户名", name = "username", required = true, dataType = "query")
    private String username;

    @Setter
    @ApiModelProperty(value = "登录密码", name = "password", required = true, dataType = "query")
    private String password;

    @Getter
    @Setter
    @ApiModelProperty(value = "用户头像")
    private String userface;

    @Getter
    @Setter
    @ApiModelProperty(value = "备注")
    private String remark;

    @Getter
    @Setter
    private List<Role> roles;

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
