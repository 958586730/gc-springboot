package com.yfc.gc.config;

import com.yfc.gc.bean.admin.SysUserDetails;
import com.yfc.gc.bean.sys.UserInfo;
import com.yfc.gc.bean.sys.WechatUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @Author WangYX
 * @Description //TODO 请求上下文工具类
 * @Date 2018/12/24 下午11:51
 **/
public class Context {

    /**
     * @Author WangYX
     * @Description //TODO 获取请求用户信息
     * @Date 2018/12/24 下午11:51
     **/
    public static Optional<SysUserDetails> getSysUser(){
        return Optional.ofNullable(SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                .filter(user->user instanceof SysUserDetails)
                .map(user->(SysUserDetails)user);
    }

    public static Optional<UserInfo> getUserInfo(){
        return getSysUser().filter(user->user instanceof UserInfo).map(user->(UserInfo) user);
    }

    public static Optional<WechatUser> getWechatUser(){
        return getSysUser().filter(user->user instanceof WechatUser).map(user->(WechatUser) user);
    }
}
