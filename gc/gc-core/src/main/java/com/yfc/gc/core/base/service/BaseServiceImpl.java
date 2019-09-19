package com.yfc.gc.core.base.service;

import com.yfc.gc.core.base.bean.BaseBean;
import com.yfc.gc.core.base.mapper.ReadBaseMapper;
import com.yfc.gc.core.base.mapper.WriterBaseMapper;
import com.yfc.gc.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class BaseServiceImpl<T extends BaseBean> implements BaseService<T> {

    @Autowired
    protected WriterBaseMapper<T> writerBaseMapper;

    @Autowired
    protected ReadBaseMapper<T> readBaseMapper;

    @Override
    public List<T> findPage(T obj) {
        return readBaseMapper.selectOrderByCreateTime(obj);
    }

    @Override
    public T find(T obj) {
        return readBaseMapper.selectOne(obj);
    }

    @Override
    public T  insert(T obj) {
        obj.initParam();
        writerBaseMapper.insertSelective(obj);
        return obj;
    }

    @Override
    public int insertList(List<T> objs) {
        if(ObjectUtil.empty(objs)){
            return 0;
        }
        objs.stream().forEach(BaseBean::initParam);
//        objs.stream().forEach(writerBaseMapper::insert);
        return writerBaseMapper.insertList(objs);
//        return objs.size();
    }

    @Override
    public int deleteByPrimaryKey(T obj) {
        return writerBaseMapper.deleteByPrimaryKey(obj);
    }

    @Override
    public int deleteBatch(T obj) {
        return writerBaseMapper.delete(obj);
    }

    @Override
    public int update(T obj) {
        obj.initEditParam();
        return writerBaseMapper.updateByPrimaryKeySelective(obj);
    }
}
