package com.yfc.gc.pc.service.sys.impl;

import com.yfc.gc.bean.sys.UserInfo;
import com.yfc.gc.core.base.service.BaseServiceImpl;
import com.yfc.gc.core.consts.GlobalConst;
import com.yfc.gc.core.login.JwtTokenUtil;
import com.yfc.gc.core.password.MD5;
import com.yfc.gc.core.util.ObjectUtil;
import com.yfc.gc.mapper.read.sys.UserInfoReadMapper;
import com.yfc.gc.mapper.writer.sys.UserInfoWriterMapper;
import com.yfc.gc.pc.service.sys.IUserInfoService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements IUserInfoService, UserDetailsService, GlobalConst {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private IUserInfoService iIUserInfoService;

    public UserInfoWriterMapper getWriterMapper() {
        return (UserInfoWriterMapper) writerBaseMapper;
    }

    private UserInfoReadMapper getReadMapper() {
        return (UserInfoReadMapper) readBaseMapper;
    }

    /**
     * @author: Cl
     * @Date: 2019/3/21 13:14
     * @Description: 加载登录后的用户信息
     */
    @Override
    public UserDetails loadUserByUsername(String authToken) throws UsernameNotFoundException {
        String userId = jwtTokenUtil.getUserIdFromToken(authToken);
        UserInfo user = new UserInfo();
        user.setId(userId);
        UserInfo resUser = getReadMapper().selectOne(user);
        validateUser(resUser, userId);
        return resUser;
    }

    /**
     * @author: Cl
     * @Date: 2019/3/21 13:13
     * @Description: 登录
     */
    @Override
    public String login(String username, String password) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        user.setPassword(MD5.toMD5(password));
        UserInfo resUser = getReadMapper().selectOne(user);
        validateUser(resUser, username);
        return jwtTokenUtil.generateToken(resUser.getId());
    }

    /**
     * @author: Cl
     * @Date: 2019/3/21 13:13
     * @Description: 验证用户是否存在
     */
    protected void validateUser(UserInfo sysUser, String username) {
        if (ObjectUtil.empty(sysUser)) {
            throw new UsernameNotFoundException("该用户或密码错误: " + username);
        }
    }

}
