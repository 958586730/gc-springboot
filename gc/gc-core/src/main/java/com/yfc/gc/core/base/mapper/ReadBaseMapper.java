package com.yfc.gc.core.base.mapper;

import com.yfc.gc.core.base.bean.BaseBean;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.base.BaseSelectMapper;
import tk.mybatis.mapper.common.condition.SelectByConditionMapper;

import java.util.List;

public interface ReadBaseMapper<T extends BaseBean> extends BaseSelectMapper<T>, SelectByConditionMapper<T> {

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param record
     * @return
     */
    @SelectProvider(type = ReadBaseProvider.class, method = "dynamicSQL")
    List<T> selectOrderByCreateTime(T record);

}
