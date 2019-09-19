package com.yfc.gc.bean.sys;

import com.yfc.gc.bean.admin.SysUserDetails;
import com.yfc.gc.core.base.bean.UUIDBaseBean;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.*;

/**
 * 表名: t_user_info
 */
@Data
@Table(name = "t_user_info")
public class UserInfo extends UUIDBaseBean implements SysUserDetails {

    public static final UserInfo EMPTY = new UserInfo();

    /**
     * 列名: USERNAME
     * 注释: 账号 账号
     */
    @Column(name = "USERNAME")
    private String username;
    /**
     * 列名: PASSWORD
     * 注释: 密码 密码
     */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * 列名: NAME
     * 注释: 用户名 用户名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 列名: PHONE
     * 注释: 电话 电话
     */
    @Column(name = "PHONE")
    private String phone;

    @Override
    public boolean empty() {
        return EMPTY.hashCode() == this.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
