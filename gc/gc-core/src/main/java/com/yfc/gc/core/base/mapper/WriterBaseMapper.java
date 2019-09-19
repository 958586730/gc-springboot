package com.yfc.gc.core.base.mapper;

import com.yfc.gc.core.base.bean.BaseBean;
import org.apache.ibatis.annotations.DeleteProvider;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.base.BaseInsertMapper;
import tk.mybatis.mapper.common.base.BaseUpdateMapper;

public interface WriterBaseMapper<T extends BaseBean> extends InsertListMapper<T>, BaseInsertMapper<T>, BaseUpdateMapper<T>, ConditionMapper<T> {

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     * 删除忽略乐观锁版本号直接删除
     * @param record
     * @return
     */
    @DeleteProvider(type = WriteBaseProvider.class, method = "dynamicSQL")
    int delete(T record);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     * 删除忽略乐观锁版本号直接删除
     * @param key
     * @return
     */
    @DeleteProvider(type = WriteBaseProvider.class, method = "dynamicSQL")
    int deleteByPrimaryKey(Object key);

}
