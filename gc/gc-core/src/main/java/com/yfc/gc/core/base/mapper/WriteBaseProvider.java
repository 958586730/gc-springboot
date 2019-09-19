package com.yfc.gc.core.base.mapper;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.provider.base.BaseDeleteProvider;

public class WriteBaseProvider extends BaseDeleteProvider {

    public WriteBaseProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    @Override
    public String delete(MappedStatement ms) {
        Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        //TODO 增加 @Version 支持
        sql.append(SqlHelper.whereAllIfColumns(entityClass, isNotEmpty(), false));
        return sql.toString();
    }

    @Override
    public String deleteByPrimaryKey(MappedStatement ms) {
        final Class<?> entityClass = getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.deleteFromTable(entityClass, tableName(entityClass)));
        //增加 @Version 乐观锁支持
        sql.append(SqlHelper.wherePKColumns(entityClass, false));
        SqlHelper.updateByExampleWhereClause();
        return sql.toString();
    }
}
