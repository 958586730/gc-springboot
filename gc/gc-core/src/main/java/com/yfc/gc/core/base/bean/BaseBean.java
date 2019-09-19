package com.yfc.gc.core.base.bean;

import com.yfc.gc.core.thread.Keys;
import com.yfc.gc.core.thread.ThreadData;
import com.yfc.gc.core.util.Empty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.Date;

@Data
public class BaseBean implements Empty {

    /**
     * 列名: CREATED_BY
     * 注释: 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 列名: CREATED_TIME
     * 注释: 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 列名: UPDATED_BY
     * 注释: 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 列名: UPDATED_TIME
     * 注释: 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 分页-页码数
     */
    @Transient
    private Integer pageNum;

    /**
     * 分页-页条数
     */
    @Transient
    private Integer pageSize;

    public void initParam() {
        setCreatedTime(new Date());
        setUpdatedTime(new Date());
        setCreatedBy(ThreadData.get(Keys.USER_ID));
        setUpdatedBy(ThreadData.get(Keys.USER_ID));
    }

    public void initEditParam() {
        setCreatedTime(null);
        setUpdatedTime(new Date());
        setCreatedBy(null);
        setUpdatedBy(ThreadData.get(Keys.USER_ID));
    }

    public void clearParam() {
        setCreatedTime(null);
        setUpdatedTime(null);
        setCreatedBy(null);
        setUpdatedBy(null);
    }
}
