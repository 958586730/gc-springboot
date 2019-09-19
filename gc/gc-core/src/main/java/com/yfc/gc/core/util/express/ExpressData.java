package com.yfc.gc.core.util.express;

import lombok.Data;

/**
 * @author: Cl
 * @Date: 2019/4/3 17:58
 * @Description: 快递100最新查询结果实体类
 */
@Data
public class ExpressData {

    /**
     * 注释: 内容
     */
    private String context;
    /**
     * 注释: 时间，原始格式
     */
    private String time;
    /**
     * 注释: 格式化后时间
     */
    private String ftime;
}

