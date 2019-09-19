package com.yfc.gc.pc.service.sys;

import com.yfc.gc.bean.sys.UserInfo;
import com.yfc.gc.core.base.service.BaseService;

public interface IUserInfoService extends BaseService<UserInfo> {

    /**
     * @author: Jdx
     * @Date: 2019-3-15 14:39
     * @Description: 用户登录
     */
    String login(String username, String password);

}
