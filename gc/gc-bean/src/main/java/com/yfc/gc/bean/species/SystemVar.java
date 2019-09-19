package com.yfc.gc.bean.species;

import com.yfc.gc.core.base.bean.UUIDBaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * 表名: t_system_var
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "t_system_var")
public class SystemVar extends UUIDBaseBean {

    public static final SystemVar EMPTY = new SystemVar();

    /**
     * 列名: CODE_CLASS
     * 注释: 代码分类
     */
    @Column(name = "CODE_CLASS")
    private String codeClass;
    /**
     * 列名: CODE_VAL
     * 注释: 代码值
     */
    @Column(name = "CODE_VAL")
    private String codeVal;
    /**
     * 列名: CODE_NM
     * 注释: 城市名称（代码名称）
     */
    @Column(name = "CODE_NM")
    private String codeNm;
    /**
     * 列名: CODE_ORDER
     * 注释: 表示顺序
     */
    @Column(name = "CODE_ORDER")
    private String codeOrder;
    /**
     * 列名: REMARK
     * 注释: 备注
     */
    @Column(name = "REMARK")
    private String remark;

    @Override
    public boolean empty() {
        return EMPTY.hashCode() == this.hashCode();
    }
}
