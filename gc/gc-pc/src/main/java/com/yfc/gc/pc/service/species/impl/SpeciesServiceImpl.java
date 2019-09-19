package com.yfc.gc.pc.service.species.impl;

import com.yfc.gc.bean.species.Species;
import com.yfc.gc.core.base.service.BaseServiceImpl;
import com.yfc.gc.mapper.read.species.SpeciesReadMapper;
import com.yfc.gc.mapper.writer.species.SpeciesWriterMapper;
import com.yfc.gc.pc.service.species.ISpeciesService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpeciesServiceImpl extends BaseServiceImpl<Species> implements ISpeciesService {

    public SpeciesWriterMapper getWriterMapper() {
        return (SpeciesWriterMapper) writerBaseMapper;
    }

    private SpeciesReadMapper getReadMapper() {
        return (SpeciesReadMapper) readBaseMapper;
    }

    /**
     * @author: Cxc
     * @Date: 2019/9/19 14:22
     * @Description: 垃圾种类一览
     */
    @Override
    public List<Species> selectSpeciesList(Species species) {
        return getReadMapper().selectSpeciesList(species);
    }
}
