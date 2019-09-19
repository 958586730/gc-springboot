package com.yfc.gc.core.util.express;

import lombok.Data;

import java.util.List;

/**
 * @author: Cl
 * @Date: 2019/4/3 17:58
 * @Description: 快递100实体类
 */
@Data
public class Express {

    /**
     * 注释: 快递配送方式
     */
    private String com;
    /**
     * 注释: 快递号
     */
    private String nu;
    /**
     * 注释: 消息体，请忽略
     */
    private String message;
    /**
     * 注释: 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态
     */
    private String state;
    /**
     * 注释: 通讯状态，请忽略
     */
    private String status;
    /**
     * 注释: 快递单明细状态标记，暂未实现，请忽略
     */
    private String condition;
    /**
     * 注释: 是否签收标记，请忽略，明细状态请参考state字段
     */
    private String ischeck;
    /**
     * 注释: 最新查询结果，数组，包含多项，全量，倒序（即时间最新的在最前），每项都是对象
     */
    private List<ExpressData> data;
}
