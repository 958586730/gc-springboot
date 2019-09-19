package com.yfc.gc.core.consts;

public interface GlobalConst {

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:41
     * @Description: 返回成功
     */
    String SUCCESS = "SUCCESS";

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:53
     * @Description: 该商品不存在
     */
    String C_30001 = "30001";

//    /**
//     * @author: Cl
//     * @Date: 2019/3/19 13:53
//     * @Description: 服务站优惠不正，请重新提交订单
//     */
//    String C_30002 = "30002";
//
//    /**
//     * @author: Cl
//     * @Date: 2019/3/19 13:53
//     * @Description: 会员优惠不正，请重新提交订单
//     */
//    String C_30003 = "30003";
//
//    /**
//     * @author: Cl
//     * @Date: 2019/3/19 13:53
//     * @Description: 客户团体优惠不正，请重新提交订单
//     */
//    String C_30004 = "30004";
//
//    /**
//     * @author: Cl
//     * @Date: 2019/3/19 13:53
//     * @Description: 优惠码优惠不正，请重新提交订单
//     */
//    String C_30005 = "30005";

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:53
     * @Description: 该订单不存在
     */
    String C_30002 = "30002";

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:53
     * @Description: 该订单已付款
     */
    String C_30003 = "30003";

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:53
     * @Description: 统一下单接口返回失败
     */
    String C_30004 = "30004";

    /**
     * @author: Cl
     * @Date: 2019/3/19 13:53
     * @Description: 结算金额不正，请重新提交订单
     */
    String C_30005 = "30005";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 优惠码不存在
     */
    String C_30006 = "30006";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 已添加优惠码
     */
    String C_30007 = "30007";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 该商品是非免费商品，请支付
     */
    String C_30008 = "30008";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 库存已不足
     */
    String C_30009 = "30009";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 您购买数量已超出购买限制
     */
    String C_30010 = "30010";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 该团体码不存在
     */
    String C_30011 = "30011";

    /**
     * @author: Cxc
     * @Date: 2019/4/10 11:54
     * @Description: 该服务站编号不存在
     */
    String C_30012 = "30012";

    /**
     * @author: Jdx
     * @Date: 2019-3-29 17:24
     * @Description: 该产品已有商品或套餐信息，不可删除
     */
    String C_40001 = "40001";

    /**
     * @author: Jdx
     * @Date: 2019-3-29 17:24
     * @Description: 该供应商已经被停用，不可更改积分
     */
    String C_40002 = "40002";

    /**
     * @author: Jdx
     * @Date: 2019-3-29 17:24
     * @Description: 该角色名已经存在
     */
    String C_40003 = "40003";

    /**
     * @author: Whf
     * @Date: 2019-4-30 11:12
     * @Description: 该改城市管理者已经被停用，不可更改积分
     */
    String C_40004 = "40004";

    /**
     * @author: Jdx
     * @Date: 2019-3-29 17:24
     * @Description: 该用户ID已经存在
     */
    String C_40005 = "40005";

    /**
     * @author: Jdx
     * @Date: 2019-3-29 17:24
     * @Description: 请导出有效数据
     */
    String C_40006 = "40006";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:24
     * @Description: 折扣比例金额开关 0：折扣金额
     */
    String IS_PERCENT_AMOUNT = "0";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:24
     * @Description: 折扣比例金额开关 1：折扣比例
     */
    String IS_PERCENT_RATE = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:55
     * @Description: 是否服务站优惠 1：是
     */
    String IS_SERVICE_STATION_DISCOUNT = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:55
     * @Description: 是否会员优惠 1：是
     */
    String IS_MEMBERSHIP_DISCOUNT = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: 是否客户团体优惠 1：是
     */
    String IS_TEAM_DISCOUNT = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: 是否套餐 0：否
     */
    String IS_NOT_PACKAGE = "0";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: 是否套餐 1：是
     */
    String IS_PACKAGE = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: defFlg 0:启用
     */
    String IS_USE = "0";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: defFlg 1:停用
     */
    String IS_NOT_USE = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: 是否被使用 0:未使用
     */
    String IS_NOT_USED = "0";

    /**
     * @author: Cl
     * @Date: 2019/3/25 1:57
     * @Description: 是否被使用 1:已使用
     */
    String IS_ALREADY_USED = "1";

    /**
     * @author: Cl
     * @Date: 2019/3/28 10:38
     * @Description: 商品/活动区分 01:商品
     */
    String IS_COMMODITY = "01";

    /**
     * @author: Cl
     * @Date: 2019/3/28 10:38
     * @Description: 商品/活动区分 02:活动
     */
    String IS_CAMPAIGNS = "02";

    /**
     * @author: Cxc
     * @Date: 2019/3/28 18:28
     * @Description: 是否活动 1:活动
     */
    String IS_CAMPAIGN = "1";

    /**
     * @author: Cxc
     * @Date: 2019/3/28 18:28
     * @Description: 是否活动 0:非活动
     */
    String IS_NOT_CAMPAIGN = "0";

    /**
     * @author: Cxc
     * @Date: 2019/3/28 18:28
     * @Description: 是否免费活动 1:免费活动
     */
    String IS_FREE = "1";

    /**
     * @author: Cxc
     * @Date: 2019/3/28 18:28
     * @Description: 是否免费活动 0:非免费活动
     */
    String IS_NOT_FREE = "0";

    /**
     * @author: Jdx
     * @Date: 2019-3-30 11:58
     * @Description: 文件上传接口文件路径
     */
    String FILE_PATH = "files";

    /**
     * @author: Jdx
     * @Date: 2019-3-30 14:47
     * @Description: 角色权限：0：无权限
     */
    String IS_NO_POWER = "0";

    /**
     * @author: Jdx
     * @Date: 2019-3-30 14:47
     * @Description: 角色权限：1：有权限
     */
    String IS_YES_POWER = "1";

    /**
     * @author: Xgz
     * @Date: 2019-3-30 18:33
     * @Description: 是否为更新数据：1：是
     */
    String IS_UPDATE_RECORD_FLG = "1";

    /**
     * @author: Whf
     * @Date: 2019-4-17 17:09
     * @Description: 是否为更新数据：0：原数据
     */
    String IS_NOT_UPDATE_RECORD_FLG = "0";

    /**
     * @author: Cl
     * @Date: 2019-4-1 14:12
     * @Description: 是否热文: 1：是
     */
    String IS_HOS = "1";

    /**
     * @author: Lbh
     * @Date: 2019-4-2 12:50
     * @Description: 发布Flg: 1：是
     */
    String IS_PUBLICED = "1";
    /**
     * @author: Lbh
     * @Date: 2019-4-2 12:50
     * @Description: 发布Flg: 1：是
     */
    String IS_NOT_PUBLICED = "0";

    /**
     * @author: Lbh
     * @Date: 2019-4-2 12:50
     * @Description: 同意还是驳回: 0：同意
     */
    String IS_YES_OR_NO = "0";

    /**
     * @author: Lbh
     * @Date: 2019-4-2 12:50
     * @Description: 同意还是驳回: 1：驳回
     */
    String IS_YES_OR_NO_N = "1";

    /**
     * @author: Zl
     * @Date: 2019-04-10 16:46
     * @Description: 旅游/非旅游：1：旅游
     */
    String IS_TRAVEL = "1";

    /**
     * @author: Whf
     * @Date: 2019-04-17 12:58
     * @Description: 指定服务范围FLG：1：指定服务范围
     */
    String IS_RANGE_FLG = "1";

    /**
     * @author: Cl
     * @Date: 2019/4/21 17:15
     * @Description: 是否减少销售基数 1：添加
     */
    String IS_SUB_SALE_BASE_NUM = "1";

    /**
     * @author: Cl
     * @Date: 2019/4/21 17:15
     * @Description: 是否添加可售数量 1：添加
     */
    String IS_ADD_COMMODITY_COUNT = "1";

    /**
     * @author: Cxc
     * @Date: 2019/4/24 14:44
     * @Description: 是否默认 1:默认
     */
    String IS_DEFAULT_FLG = "1";

    /**
     * @author: Cxc
     * @Date: 2019/4/24 14:44
     * @Description: 是否默认 0:默认
     */
    String IS_NOT_DEFAULT_FLG = "0";

    /**
     * @author: Cxc
     * @Date: 2019/5/7 15:11
     * @Description: 代码名称 005 启用
     */
    String IS_ACTIVATE = "005";

    /**
     * @author: Cxc
     * @Date: 2019/5/7 15:14
     * @Description: 代码名称 审批状态
     * 代码信息表
     * 分类006
     * 001:审核中
     * 002:审核通过
     * 003:已驳回
     */
    String IS_IN_THE_REVIEW = "001";

    /**
     * @author: Cxc
     * @Date: 2019/5/7 15:14
     * @Description: 代码名称 审批状态
     * 代码信息表
     * 分类006
     * 001:审核中
     * 002:审核通过
     * 003:已驳回
     */
    String IS_IN_AUDIT_PASS = "002";

    /**
     * @author: Cxc
     * @Date: 2019/5/7 15:14
     * @Description: 代码名称 审批状态
     * 代码信息表
     * 分类006
     * 001:审核中
     * 002:审核通过
     * 003:已驳回
     */
    String IS_IN_REJECT = "003";

    /**
     * @author: Lbh
     * @Date: 2019/5/9 18:45
     * @Description: 客户团体保存时验证
     */
    String MODULEFLAG_CUSTOMER_TEAM = "1";
    /**
     * @author: Lbh
     * @Date: 2019/5/9 18:45
     * @Description: 供应商保存时验证
     */
    String MODULEFLAG_SUPPLIER_INFO = "2";
    /**
     * @author: Lbh
     * @Date: 2019/5/9 18:45
     * @Description: 城市和作者保存时验证
     */
    String MODULEFLAG_CITY_MANAGER = "3";
    /**
     * @author: Lbh
     * @Date: 2019/5/9 18:45
     * @Description: 城市和作者保存时验证
     */
    String DEL_FLG_0 = "0";
    /**
     * @author: Lbh
     * @Date: 2019/5/9 18:45
     * @Description: 城市和作者保存时验证
     */
    String DEL_FLG_1 = "1";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 10:13
     * @Description: 申请分类 007 001
     */
    String IS_CLASSIFICATION = "007";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 15:17
     * @Description: 申请分类 007 001 新建供应商
     */
    String IS_NEW_SUPPLIER = "001";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 15:18
     * @Description: 申请分类 007 002 信息变更
     */
    String IS_INFORMATION_CHANGES = "002";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 15:19
     * @Description: 申请分类 007 003 积分变更
     */
    String IS_INTEGRAL_CHANGE = "003";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 15:21
     * @Description: 申请分类 007 004 停用
     */
    String IS_DISABLE = "004";

    /**
     * @author: Cxc
     * @Date: 2019/5/13 15:22
     * @Description: 申请分类 007 005 启用
     */
    String IS_TO_ENABLE_THE = "005";

    /**
     * @author: Cxc
     * @Date: 2019/5/14 17:16
     * @Description: 该服务站编号已存在
     */
    String C_500001 = "500001";

    /**
     * @author: Cxc
     * @Date: 2019/5/14 17:20
     * @Description: 无当前城市管理者
     */
    String C_500002 = "500002";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 该供应商已被停用，不做审核操作
     */
    String C_60001 = "60001";
    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 审核操作成功
     */
    String C_60002 = "60002";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 该城市管理者已被停用，不做审核操作
     */
    String C_60003 = "60003";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 已经在审核中，不可修改
     */
    String C_60004 = "C_60004";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 对应的产品已被停用无法执行更新操作
     */
    String C_60005 = "C_60005";

    /**
     * @author: Whf
     * @Date: 2019/7/30 16:33
     * @Description: 经度整数位不能超过3位
     */
     String C_60006 = "C_60006";

    /**
     * @author: Whf
     * @Date: 2019/7/30 16:33
     * @Description: 纬度整数位不能超过3位
     */
    String C_60007 = "C_60007";

    /**
     * @author: Whf
     * @Date: 2019/7/30 16:33
     * @Description: 经度只能输入数值类型
     */
    String C_60008 = "C_60008";

    /**
     * @author: Whf
     * @Date: 2019/7/30 16:33
     * @Description: 纬度只能输入数值类型
     */
    String C_60009 = "C_60009";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 审核状态：审核中
     */
    String STATUS_001 = "001";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 是旅游
     */
    String IsTravel = "1";

    /**
     * @author: Lbh
     * @Date: 2019/5/10 11:54
     * @Description: 订单状态：已完成
     */
    String IS_COMPLETE = "006";


}
