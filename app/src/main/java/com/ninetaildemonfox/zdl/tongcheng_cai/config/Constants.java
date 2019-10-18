package com.ninetaildemonfox.zdl.tongcheng_cai.config;

/**
 * 创建人： Nine tails fox
 * 创建时间： 2019/9/10
 * 功能描述：
 * 联系方式：1037438704@qq.com
 *
 * @author NineTailDemonFox
 */

public interface Constants {
    /**
     * 用户注册
     */
    String REGISTER = "Member/register";
    /**
     * 查看即时/完场分析
     */
    String GETFOOTBALLMATCHREWARDSINFO = "OpenRewards/getFootballMatchRewardsInfo";
    /**
     * 查询用户的默认收货地址
     */
    String FINDDEFAULTADDRESS = "Member/findDefaultAddress";

    /**
     * 账号密码登录
     */
    String LOGIN = "Member/login";
    /**
     * 找回密码
     */
    String RESETPASSWORD = "Member/resetPassword";


    /**
     * 获取省市区列表
     */
    String GETREGIONLIST = "Member/getRegionList";


    /**
     * 创建收货地址
     */
    String ADDRECEIVEADDRESS = "Member/addReceiveAddress";

    /**
     * 足彩各个玩法列表数据【
     */
    String GETFOOTBALLPLAYLIST = "FootballMatch/getFootballPlayList";

    /**
     * 查看发起跟单详情
     */
    String GETAGAINORDERSENDINFO = "AgainOrders/getAgainOrderSendInfo";

    /**
     * 创建收货地址
     */
    String BINDBANKCARD = "Wallet/bindBankCard";


    /**
     * 设置默认收货地址
     */
    String SAVEDEFAULTADDRESS = "Member/saveDefaultAddress";

    /**
     * 编辑收货地址
     */
    String SAVERECEIVEADDRESS = "Member/saveReceiveAddress";

    /**
     * 用户收货地址列表
     */
    String GETRECEIVEADDRESSLIST = "Member/getReceiveAddressList";


    /**
     * 查询收货地址详情
     */
    String GETRECEIVEADDRESSINFO = "Member/getReceiveAddressInfo";


    /**
     * 删除用户收货地址
     */
    String DELRECEIVEADDRESS = "Member/delReceiveAddress";


    /**
     * 积分商城订单列表
     */
    String GETEXCHANGEINTEGRALLOGLIST = "Member/getExchangeIntegralLogList";

    /**
     * 彩票订单列表
     */
    String GETORDERSLIST = "Orders/getOrdersList";

    /**
     * 我的——跟单订单与我的跟单
     */
    String AGAINORDERSLIST = "AgainOrders/againOrdersList";


    /**
     * 积分商城订单详情
     */
    String GETEXCHANGEINTEGRALLOGINFO = "Member/getExchangeIntegralLogInfo";


    /**
     * 积分订单确认收货
     */
    String CONFIRMINTEGRALORDER = "Member/confirmIntegralOrder";


    /**
     * 修改用户个人资料
     */
    String EDITMEMBERBASEDATA = "Member/editMemberBaseData";


    /**
     * 用户查看个人资料
     */
    String GETMEMBERBASEDATA = "Member/getMemberBaseData";


    /**
     * 用户修改密码
     */
    String EDITPASSWORD = "Member/editPassword";


    /**
     * 用户换绑手机号
     */
    String EDITACCOUNT = "Member/editAccount";

    /**
     * 个人中心（我的页面）
     */
    String MEMBERCENTER = "Member/memberCenter";


    /**
     * 我评论的
     */
    String MYPOSTCOMMENTS = "Member/myPostComments";


    /**
     * 发送短信验证码
     */
    String SENDVERIFY = "Member/sendVerify";


    /**
     * 验证短信验证码
     */
    String CHECKVERIFY = "Member/checkVerify";


    /**
     * 我的邀请码
     */
    String GETMEMBERPROMOTEINFO = "Member/getMemberPromoteInfo";


    /**
     * 积分商场列表
     */
    String GETINTEGRALGOODSLIST = "IntegralShop/getIntegralGoodsList";

    /**
     * 用户积分明细
     */
    String GETINTEGRALLOGLIST = "Member/getIntegralLogList";

    /**
     * 实名认证
     */
    String REALNAMECERTIFICATION = "Wallet/realNameCertification";


    /**
     * 积分商品详情
     */
    String GETINTEGRALGOODSINFO = "IntegralShop/getIntegralGoodsInfo";
    /**
     * 提现明细
     */
    String GETWITHDRAWLOGLIST = "Wallet/getWithdrawLogList";


    /**
     * 账本明细
     */
    String GETMONEYLOGLIST = "Wallet/getMoneyLogList";


    /**
     * 用户签到列表
     */
    String GETMEMBERSIGNLIST = "Index/getMemberSignList";
    /**
     * 用户签到列表
     */
    String ADDMEMBERSIGN = "Index/addMemberSign";


    /**
     * 用户签到列表
     */
    String EXCHANGEINTEGRALGOODS = "IntegralShop/exchangeIntegralGoods";
    /**
     * 我评论的
     */
    String GETCOMMENTSLIST = "PostComments/getCommentsList";

    /**
     * 首页数据
     */
    String GETINDEXDATA = "Index/getIndexData";
    /**
     * 中奖信息
     */
    String GETWINLOGLIST = "Index/getWinLogList";

    /**
     * 中奖信息
     */
    String GETFOOTBALLMATCHREWARDSLIST = "OpenRewards/getFootballMatchRewardsList";

    /**
     * 开奖
     */
    String OPENFOOTBALLMATCHREWARDSLIST = "OpenRewards/openFootballMatchRewardsList";

    /**
     * 往期开奖
     */
    String BEFOREFOOTBALLMATCHREWARDSLIST = "OpenRewards/beforeFootballMatchRewardsList";
    /**
     * 帖子列表
     */
    String SELECTPOSTLIST = "Post/selectPostList";
    /**
     * 查看帖子详情
     */
    String FINDPOSTINFO = "Post/findPostInfo";


    /**
     * 帖子评论列表
     */
    String SELECTPOSTCOMMENTSLIST = "PostComments/selectPostCommentsList";


    /**
     * 创建帖子评论
     */
    String ADDPOSTCOMMENTS = "PostComments/addPostComments";


    /**
     * 用户发帖
     */
    String ADDPOST = "Post/addPost";

    /**
     * 修改帖子
     */
    String EDITPOST = "Post/editPost";

    /**
     * 点赞帖子
     */
    String GIVELIKE = "ClickLikeLog/giveLike";

    /**
     * 删除帖子
     */
    String DELPOST = "Post/delPost";


    /**
     * 银行列表
     */
    String GETBANKLIST = "Wallet/getBankList";


    /**
     * 查询绑定银行卡详情
     */
    String FINDBANKCARD = "Wallet/findBankCard";


    /**
     * 推荐列表
     */
    String PUSHORDERSLIST = "AgainOrders/pushOrdersList";


    /**
     * 余额清账(申请提现)
     */
    String ADDWITHDRAWLOG = "Wallet/addWithdrawLog";


    /**
     * 账户储值
     */
    String ADDRECHARGELOG = "Wallet/addRechargeLog";


    /**
     * 推荐详情
     */
    String PUSHORDERSINFO = "AgainOrders/pushOrdersInfo";

    /**
     * 推荐下单
     */
    String PAYPUSHORDER = "AgainOrders/payPushOrder";


    /**
     * 推荐下单
     */
    String GETMEMBERAGAINSENDRECORDLIST = "AgainOrders/getMemberAgainSendRecordList";


    /**
     * 用户跟单
     */
    String CREATEAGAINORDER = "AgainOrders/createAgainOrder";


    /**
     * 发起跟单
     */
    String CREATEAGAINORDERSEND = "AgainOrders/createAgainOrderSend";


    /**
     * 发起跟单列表
     */
    String GETAGAINORDERSSENDLIST = "AgainOrders/getAgainOrdersSendList";

    /**
     * 赛事类型筛选
     */
    String GETMATCHTYPELIST = "FootballMatch/getMatchTypeList";

    /**
     * 跟单规则、注册协议、积分规则、签到规则
     */
    String GETNORMALRULEINFO = "Index/getNormalRuleInfo";

    /**
     * 跟单规则、注册协议、积分规则、签到规则
     */
    String GETMONEYRULELIST = "Index/getMoneyRuleList";

    /**
     * 彩票订单详情
     */
    String GETORDERINFO = "Orders/getOrderInfo";

    /**
     * 彩票订单详情---撤单
     */
    String CANCELORDER = "Orders/cancelOrder";

    /*
    * 下单
    * */
    String CHOOSEFOOTBALLMATCH = "FootballMatch/chooseFootballMatch";

    /*
     * 确认下单
     * */
    String CONFIRMFOOTBALLORDER = "FootballMatch/confirmFootballOrder";
}
