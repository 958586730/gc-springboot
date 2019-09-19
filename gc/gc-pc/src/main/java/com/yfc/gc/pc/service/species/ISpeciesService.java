package com.yfc.gc.pc.service.species;


import com.yfc.gc.bean.species.Species;
import com.yfc.gc.core.base.service.BaseService;

import java.util.List;

public interface ISpeciesService extends BaseService<Species> {

    /**
     * @author: Cxc
     * @Date: 2019/9/19 14:22
     * @Description: 垃圾种类一览
     */
    List<Species> selectSpeciesList(Species species);

}
