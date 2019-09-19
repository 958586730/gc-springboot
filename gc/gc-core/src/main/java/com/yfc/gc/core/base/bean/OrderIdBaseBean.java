package com.yfc.gc.core.base.bean;

import com.yfc.gc.core.util.UUIDUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderIdBaseBean extends CustomIDBaseBean {

    @Override
    public void initParam() {

        setId(UUIDUtil.orderId());
        super.initParam();
    }

    @Override
    public void clearParam() {
        setId(null);
        super.clearParam();
    }
}