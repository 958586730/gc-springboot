package com.yfc.gc.pc.rest.species;

import com.yfc.gc.bean.species.Species;
import com.yfc.gc.pc.service.species.ISpeciesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Cxc
 * @Date: 2019/9/19 11:34
 * @Description: 垃圾种类查询
 */
@Slf4j
@RestController
@RequestMapping("/type")
public class SpeciesRest {

    @Resource
    private ISpeciesService iSpeciesService;

    /**
     * @author: Cxc
     * @Date: 2019/9/19 14:22
     * @Description: 垃圾种类一览
     */
    @PostMapping("/selectTypeList")
    public List<Species> selectSpeciesList(@RequestBody Species species) {
        return iSpeciesService.selectSpeciesList(species);
    }
}
