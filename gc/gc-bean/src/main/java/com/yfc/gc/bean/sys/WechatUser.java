package com.yfc.gc.bean.sys;

import com.yfc.gc.bean.admin.SysUserDetails;
import com.yfc.gc.core.base.bean.UUIDBaseBean;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;

/**
 * 表名: t_wechat_user
 */
@Data
@Table(name = "t_wechat_user")
public class WechatUser extends UUIDBaseBean implements SysUserDetails {

    public static final WechatUser EMPTY = new WechatUser();

    /**
     * 列名: OPEN_ID
     * 注释: OPEN_ID OPEN_ID
     */
    @Column(name = "OPEN_ID")
    private String openId;
    /**
     * 列名: IMG
     * 注释: 用户头像 用户头像
     */
    @Column(name = "IMG")
    private String img;
    /**
     * 列名: NAME
     * 注释: 用户名 用户名
     */
    @Column(name = "NAME")
    private String name;
    /**
     * 列名: SEX
     * 注释: 性别 0：女  1：男
     */
    @Column(name = "SEX")
    private String sex;
    /**
     * 列名: PHONE
     * 注释: 手机号 手机号
     */
    @Column(name = "PHONE")
    private String phone;
    /**
     * 列名: LONGITUDE_LATITUDE
     * 注释: 经纬度 经纬度
     */
    @Column(name = "LONGITUDE_LATITUDE")
    private String longitudeLatitude;
    /**
     * 列名: INTEGRAL
     * 注释: 积分 积分
     */
    @Column(name = "INTEGRAL")
    private Integer integral;

    @Override
    public boolean empty() {
        return EMPTY.hashCode() == this.hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return getOpenId();
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
