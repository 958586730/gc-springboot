package com.yfc.gc.core.base.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(callSuper = true)
public class IncreaseIDBaseBean extends BaseBean {

    /**
     * 列名: ID
     * 注释: 主键id
     */
    @Column(name = "ID")
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

}
