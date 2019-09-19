package com.yfc.gc.config.aop.rest;

import com.yfc.gc.bean.admin.SysUserDetails;
import com.yfc.gc.config.Context;
import com.yfc.gc.core.thread.Keys;
import com.yfc.gc.core.thread.ThreadData;
import com.yfc.gc.core.util.JSONUtil;
import com.yfc.gc.core.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class RestAdvisor {

    @Around(value = "com.yfc.gc.config.aop.CommPointCut.rest()")
    public Object filter(ProceedingJoinPoint joinPoint) throws Throwable{
        ThreadData.set(Keys.USER_ID, Context.getSysUser().map(SysUserDetails::getUsername).orElse(""));
        if(log.isDebugEnabled()) {
            String id = UUIDUtil.shortUuid();
            ThreadData.set(Keys.REQUEST_ID, id);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            log.debug("requestBody  id: -->{}<--, url: {}, method: {}.{}, 参数: {}"
                    , id
                    , request.getRequestURI()
                    , joinPoint.getTarget().getClass().getName()
                    , joinPoint.getSignature().getName()
                    , JSONUtil.toJson(joinPoint.getArgs()));
        }
        return joinPoint.proceed();
    }

}
