package com.yfc.gc.bean.species;

import com.yfc.gc.core.base.bean.UUIDBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 表名: t_type
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_type")
public class Species extends UUIDBaseBean {

    public static final Species EMPTY = new Species();

    /**
     * 列名: TYPE_NAME
     * 注释: 种类名
     */
    @Column(name = "TYPE_NAME")
    private String typeName;
    /**
     * 列名: TYPE_NAME_ENG
     * 注释: 英文名
     */
    @Column(name = "TYPE_NAME_ENG")
    private String typeNameEng;
    /**
     * 列名: LOGO
     * 注释: 标志
     */
    @Column(name = "LOGO")
    private String logo;
    /**
     * 列名: IMG
     * 注释: 图标
     */
    @Column(name = "IMG")
    private String img;
    /**
     * 列名: RETRACTION_IMG
     * 注释: 缩略图
     */
    @Column(name = "RETRACTION_IMG")
    private String retractionImg;
    /**
     * 列名: CITY
     * 注释: 所属地区
     */
    @Column(name = "CITY")
    private String city;
    /**
     * 列名: TYPE_DESCRIBE
     * 注释: 描述
     */
    @Column(name = "TYPE_DESCRIBE")
    private String typeDescribe;
    /**
     * 列名: TYPE_ORDER
     * 注释: 表示顺序
     */
    @Column(name = "TYPE_ORDER")
    private String typeOrder;

    @Override
    public boolean empty() {
        return EMPTY.hashCode() == this.hashCode();
    }
}
