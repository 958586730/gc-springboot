package com.yfc.gc.core.base.service;

import com.yfc.gc.core.base.bean.BaseBean;
import com.yfc.gc.core.consts.SystemConst;

import java.util.List;

public interface BaseService<T extends BaseBean> extends SystemConst {

    /**
     * 分页查询
     * @param obj
     * @return
     */
    List<T> findPage(T obj);

    /**
     * 查询单条数据
     * @param obj
     * @return
     */
    T find(T obj);

    /**
     * 单条新增
     * @param obj
     * @return
     */
    T insert(T obj);

    /**
     * 列表新增
     * @param objs
     * @return
     */
    int insertList(List<T> objs);

    /**
     * 删除
     * @param obj
     * @return
     */
    int deleteByPrimaryKey(T obj);

    /**
     * 删除多个
     *
     * @param obj
     * @return
     */
    int deleteBatch(T obj);

    /**
     * 更新
     * @param obj
     * @return
     */
    int update(T obj);

}
