package com.yfc.gc.pc.rest.sys;

import com.yfc.gc.bean.sys.UserInfo;
import com.yfc.gc.config.Context;
import com.yfc.gc.core.consts.GlobalConst;
import com.yfc.gc.core.util.Assert;
import com.yfc.gc.pc.service.sys.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: Cl
 * @Date: 2019/7/26 10:48
 * @Description:
 */
@Slf4j
@RestController
public class UserInfoRest implements GlobalConst {

    @Resource
    private IUserInfoService iIUserInfoService;

    /**
     * @author: Cl
     * @Date: 2019/3/21 14:10
     * @Description: 登录并返回token
     */
    @PostMapping("/login")
    public String getToken(@RequestBody UserInfo userInfo) {
        Assert.empty(userInfo.getUsername(), "登录名不可为空");
        Assert.empty(userInfo.getPassword(), "登录密码不可为空");
        return iIUserInfoService.login(userInfo.getUsername(), userInfo.getPassword());
    }

    /**
     * @author: Cl
     * @Date: 2019/4/10 16:25
     * @Description: 登出
     */
    @PostMapping("/logoff")
    public void logoff() {
        log.info(Context.getUserInfo().map(UserInfo::getUsername).orElse("") + ": 登出了！");
    }

}
