package com.yfc.gc.core.util;

import com.yfc.gc.core.exception.NoParamException;

/**
 * 断言类
 *
 * @author WangYX
 */
public class Assert {

    /**
     * 验证对象是否为NULL,空字符串，空数组，空的Collection或Map(只有空格的字符串也认为是空串)
     *
     * @param @param obj
     * @param @param code
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws
     * @Title: empty
     */
    public static void empty(Object obj, String code, String... args) {
        empty(obj, new NoParamException(code, args));
    }

    /**
     * 为空抛出异常
     *
     * @param obj
     * @param re
     */
    public static void empty(Object obj, RuntimeException re) {
        if (ObjectUtil.empty(obj)) {
            throw re;
        }
    }

    /**
     * 根据条件抛出异常
     *
     * @param cond
     * @param re
     */
    public static void condition(Boolean cond, RuntimeException re) {
        if (cond != null && cond) {
            throw re;
        }
    }

    /**
     * 根据条件抛出异常
     *
     * @param cond
     * @param code
     */
    public static void condition(Boolean cond, String code, String... args) {
        if (cond != null && cond) {
            condition(cond, new NoParamException(code, args));
        }
    }

}
