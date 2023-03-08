package module.common.utils;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @describe: 请求链接工具类
 * @date: 2020/1/10
 * @author: Mr Bai
 */
public class URLUtils {

    //中文
    public final static String HOST_CN = "http://tst.api.yiguo.fun/";

    //英文
    public final static String HOST_EN = "http://tst.api.yiguo.fun/";


    public static String TOKEN_KEY = "access_token";

    public static final String USER_CERFICATION = "system/user/getUserCertification?" + TOKEN_KEY+ "=";
    public static final String MY_QRCODE = "system/my/barcode?" + TOKEN_KEY+"=";
    public static final String IM_CREATE_TEAM = "system/user/im/team/create?"+ TOKEN_KEY+"=";

    public static final String IM_DEL_GROUPING = "system/user/im/group/del?"+ TOKEN_KEY+"=";
    public static final String IM_ADD_GROUPING = "system/user/im/group/save?"+ TOKEN_KEY+"=";
    /*分组*/
    public static final String IM_GROUPING_LIST = "system/user/im/group/query?" + TOKEN_KEY+"=";
    public static final String IM_USER_LIST = "system/user/im/query?"+ TOKEN_KEY+"=";
    /*我加入的群列表*/
    public static final String IM_MY_TEAM_LIST = "system/user/im/my/team?"+ TOKEN_KEY+"=";

    public static final String IM_SAME_CITY_TEAM_LIST = "system/user/im/city/team/query?"+ TOKEN_KEY+"=";
    public static final String IM_SAME_CITY_USER_LIST = "system/user/im/city/query?" + TOKEN_KEY+"=";
    //删除好友
    public static final String IM_DELETE_FRIEND = "system/user/im/friend/del?" + TOKEN_KEY+"=";
    public static final String IM_ADD_BLACKLIST = "system/user/im/friend/to/black?" + TOKEN_KEY+"=";

    public static final String IM_UPDATE_USER_GROUP = "system/user/im/friend/update?" + TOKEN_KEY+"=";

    public static final String IM_CONTACTS_GROUP_LIST = "system/user/im/friend/query?" + TOKEN_KEY+"=";

    public static final String STORE_LIST = "system/store/page/query?" + TOKEN_KEY+"=";
    //申请店铺
    public static final String APPLY_STORE = "system/store/merchant/create?"+ TOKEN_KEY+"=";
    //后台系统用户信息
    public static final String SYSTEM_USER_INFO = "system/admin/user/sys/info?" + TOKEN_KEY+"=";
    //账单数据
    public static final String BILL_LIST = "shop/account/page/query?"+TOKEN_KEY+"=";
    /*更新扩展的用户信息*/
    public static final String UPDATE_EXTEND_USER_INFO = "system/user/extend/update?"+TOKEN_KEY+"=";
    /*扩展的用户信息*/
    public static final String EXTEND_USER_INFO = "system/user/extend/info?"+TOKEN_KEY+"=";
    /*相册：更新目录*/
    public static final String ALBUM_UPDATE_DIR = HOST_CN +"system/admin/merchant/updateMerchantFileFolder?"+TOKEN_KEY+"=";
    /*相册：图片列表*/
    public static final String ALBUM_PICTURES = HOST_CN +"system/admin/merchant/page/queryMerchantImage?"+TOKEN_KEY+"=";
    /*相册：添加图片*/
    public static final String ALBUM_ADD_PICTURE = HOST_CN +"system/admin/merchant/createMerchantImage?"+TOKEN_KEY+"=";
    /*是否是商户账号*/
    public static final String IS_MERCH = HOST_CN +"system/admin/privilege/user/store?"+TOKEN_KEY+"=";
    /*新建相册*/
    public static final String ALBUM_ADD_DIR = HOST_CN +"system/admin/merchant/createMerchantFileFolder?"+TOKEN_KEY+"=";
    /*后台用户信息*/
    public static final String BACKSTAGE_USER_INFO = HOST_CN +"system/admin/user/sys/info?"+TOKEN_KEY+"=";
    /*注册*/
    public static final String REGISTER = HOST_CN +"system/user/mobile/register";

    /*支付*/
    public static final String PAY = HOST_CN +"shop/order/pay?"+TOKEN_KEY+"=";

    /*获取收货地址列表*/
    public static final String ADDRESS_LIST = HOST_CN +"system/user/shipping/query?"+TOKEN_KEY+"=";

    /*保存收货地址*/
    public static final String SAVE_ADDRESS = HOST_CN +"system/user/shipping/save?"+TOKEN_KEY+"=";

    /*删除地址*/
    public static final String DELETE_ADDRESS = HOST_CN +"system/user/shipping/del?"+TOKEN_KEY+"=";

    /*购物车所有数据*/
    public static final String ALL_SHOPPING_CART = HOST_CN +"shop/cart?"+TOKEN_KEY+"=";

    /*更新购物车数量*/
    public static final String UPDATE_SHOPPINGCART_NUMBER = HOST_CN +"shop/cart/update?"+TOKEN_KEY+"=";

    /*删除购物车商品*/
    public static final String DELETE_SHOPPINGCART = HOST_CN +"shop/cart/del?"+TOKEN_KEY+"=";
    /*删除订单*/
    public static final String ORDER_DELETE = HOST_CN +"shop/order/shipping/deleteOrder?"+TOKEN_KEY+"=";

    /*取消订单*/
    public static final String ORDER_CANCEL = HOST_CN +"shop/order/shipping/cancleOrder?"+TOKEN_KEY+"=";

    /*订单详情*/
    public static final String ORDER_DETAIL = HOST_CN +"shop/order/info?"+TOKEN_KEY+"=";

    /*订单列表实体*/
    public static final String ORDER_LIST = HOST_CN +"shop/order/page/query?"+TOKEN_KEY+"=";

    /*下单*/
    public static final String CREATE_ORDER = HOST_CN +"shop/cart/order?"+TOKEN_KEY+"=";

    /*获取默认收货人信息*/
    public static final String DEFAULT_USER_SHIPPING = HOST_CN + "system/user/shipping/query?"+TOKEN_KEY+"=";


    /*账户余额*/
    public static final String BALANCE = HOST_CN +"shop/account/user/info?"+TOKEN_KEY+"=";

    /*获取订单支付信息*/
    public static final String ORDER_PAY_INFO = HOST_CN +"shop/order/pay/info?"+TOKEN_KEY+"=";

    /*相册目录列表*/
    public static final String ALBUM_DIR = HOST_CN +"system/admin/merchant/page/queryMerchantFileFolder?"+TOKEN_KEY+"=";

    /*店铺商品列表*/
    public static final String SHOP_GOODS = "shop/store/goods/page/query?"+TOKEN_KEY+"=";
    /*获取生活圈分类*/
    public static final String CLIQUE_CATEGORY = HOST_CN +"circle/mediaType/typeQuery?"+TOKEN_KEY+"=";

    /*生活圈，推荐列表*/
    public static final String DYNAMIC_RECOMMEND_LIST = HOST_CN +"circle/media/page/query/referrer?"+TOKEN_KEY+"=";

    /*生活圈，好友列表*/
    public static final String DYNAMIC_FRIEND_LIST = HOST_CN +"circle/media/page/query/friend?"+TOKEN_KEY+"=";

    /*生活圈，同城列表*/
    public static final String DYNAMIC_CITY_LIST = HOST_CN +"circle/media/page/query/city?"+TOKEN_KEY+"=";

    /*生活圈，其他列表*/
    public static final String DYNAMIC_OTHER_LIST = HOST_CN +"circle/media/page/query/typeId?"+TOKEN_KEY+"=";

    /*礼物列表*/
    public static final String GIFT_CATEGORY = HOST_CN +"system/gold/gift/giftType?"+TOKEN_KEY+"=";

    /*根据分类id获取礼物列表*/
    public static final String GIFT_LIST = HOST_CN +"system/gold/gift/page/query?"+TOKEN_KEY+"=";

    /*钱包，账户余额*/
    public static final String WALLET = HOST_CN +"shop/gold/account/user/info?"+TOKEN_KEY+"=";

    /*赠送礼物*/
    public static final String GIVE_GIFT = HOST_CN +"system/gold/gift/sendGift?"+TOKEN_KEY+"=";

    /*点赞*/
    public static final String ENDORSE = "circle/mediaPraise/praise?"+TOKEN_KEY+"=";

    /*动态收藏*/
    public static final String COLLECT_DYNAMIC =  "circle/mediaFav/favorite?"+TOKEN_KEY+"=";

    /*获取关注状态*/
    public static final String ATTENTION_STATUS = HOST_CN +"circle/mediaLike/getLikeStatus?"+TOKEN_KEY+"=";

    /*关注*/
    public static final String ATTENTION = HOST_CN +"circle/mediaLike/like?"+TOKEN_KEY+"=";


    /*关注用户*/
    public static final String ATTENTION_USER = "circle/mediaLike/like?"+TOKEN_KEY+"=";

    /*评论列表*/
    public static final String COMMENT_LIST =  HOST_CN +"circle/mediaComment/getCommentPage?"+TOKEN_KEY+"=";

    /*发表评论*/
    public static final String PUBLISH_COMMENT = HOST_CN +"circle/mediaComment/comment?"+TOKEN_KEY+"=";

    /*获取上传秘钥*/
    public static final String UPLOAD_SIGN = HOST_CN +"system/upload/sign?"+TOKEN_KEY+"=";

    /*发布动态*/
    public static final String PUBLISH_DYNAMIC = HOST_CN +"circle/media/lifeCircleRelease?"+TOKEN_KEY+"=";

    /*推荐商品*/
    public static final String RECOMMEND_GOODS = HOST_CN +"shop/goods/page/querySell?"+TOKEN_KEY+"=";

    /*刷新token*/
    public static final String REFRESH_TOKEN = HOST_CN +"system/user/token/refresh";

    /*我的动态数据*/
    public static final String MY_DYNAMIC = "circle/myMedia/getMyMediaData?"+TOKEN_KEY+"=";

    /*我的评论列表*/
    public static final String MY_COMMENT = HOST_CN +"circle/myMedia/getCommentData?"+TOKEN_KEY+"=";

    /*我的生活圈：赞列表*/
    public static final String MY_ENDORSES = HOST_CN +"circle/myMedia/getPraiseData?"+TOKEN_KEY+"=";

    /*打赏给我礼物*/
    public static final String MY_GIFTS = HOST_CN +"circle/myMedia/getGiftData?"+TOKEN_KEY+"=";

    /*动态搜索*/
    public static final String DYNAMIC_SEARCH = HOST_CN +"circle/media/page/query/titleLike?"+TOKEN_KEY+"=";

    /*音乐类别*/
    public static final String MUSIC_CATEGORY = HOST_CN +"circle/mediaMusic/musicType?"+TOKEN_KEY+"=";

    /*更加类别获取音乐*/
    public static final String MUSICS_BY_CATEGORY = HOST_CN +"circle/mediaMusic/musicPageByType?"+TOKEN_KEY+"=";

    /*联系人资料*/
    public static final String CONTACTS = HOST_CN +"system/user/extend/info?"+TOKEN_KEY+"=";


    /*发送红包*/
    public static final String SEND_RED_PACKET = HOST_CN +"system/gold/redPacket/sendRedPacket?"+TOKEN_KEY+"=";

    /*获取好友列表*/
    public static final String FRIENDS = HOST_CN +"system/user/im/friend/query?"+TOKEN_KEY+"=";

    /*领取红包*/
    public static final String GET_RED_PACKET = HOST_CN +"system/gold/redPacket/openRedPacket?"+TOKEN_KEY+"=";

    /*收藏的商品*/
    public static final String COLLECT_GOODS = HOST_CN +"shop/goods/fav/page/query?"+TOKEN_KEY+"=";

    /*收藏的动态列表*/
    public static final String COLLECT_DYNAMICIE_LIST = HOST_CN +"circle/myMedia/page/query/favorite?"+TOKEN_KEY+"=";

    /*商品详情*/
    public static final String GOODS_DETAIL = HOST_CN +"shop/goods/info?"+TOKEN_KEY+"=";

    /*获取省地址*/
    public static final String ADDRESS = HOST_CN +"system/address/query?"+TOKEN_KEY+"=";

    /*充值协议*/
    public static final String PROTOCOL = HOST_CN +"system/protocol/info";

    /*充值数据列表*/
    public static final String RECHARGE_DATA = HOST_CN +"system/gold/topUp/query?"+TOKEN_KEY+"=";

    /*创建充值订单*/
    public static final String RECHARGE_ORDER = HOST_CN +"system/gold/topUpOrder?"+TOKEN_KEY+"=";

    /*浏币充值支付*/
    public static final String VIRTUAL_PAY = HOST_CN +"system/gold//ali/topUp?"+TOKEN_KEY+"=";

    /*浏览排行*/
    public static final String LOOK_RANKING = HOST_CN +"circle/rankings/page/mediaByViewNum?"+TOKEN_KEY+"=";

    /*富豪排行榜*/
    public static final String RICH_RANKING = HOST_CN +"circle/rankings/page/richList?"+TOKEN_KEY+"=";

    /*明星作品排行*/
    public static final String STAR_WORK_RANKING = HOST_CN +"circle/rankings/page/starsMediaList?"+TOKEN_KEY+"=";

    /*点赞排行榜*/
    public static final String ENDORSE_RANKING = HOST_CN +"circle/rankings/page/praiseList?"+TOKEN_KEY+"=";

    /*好友请求列表*/
    public static final String FRIEND_REQUEST = HOST_CN +"system/user/im/operation/query?"+TOKEN_KEY+"=";

    /*通过好友请求*/
    public static final String FRIEND_PASS_REQUEST = HOST_CN +"system/user/im/operation/sure?"+TOKEN_KEY+"=";

    /*修改用户信息*/
    public static final String MODIFY_USER_INFO =  "system/user/extend/update?"+TOKEN_KEY+"=";

    /*获取用户认证信息*/
    public static final String USER_CERTIFICATION_INFO = HOST_CN +"system/user/getUserCertification?"+TOKEN_KEY+"=";

    /*身份证认证*/
    public static final String SUBMIT_IDENTITY_CARD = HOST_CN +"system/tencentapi/OCRIdCard?"+TOKEN_KEY+"=";

    /*动态删除*/
    public static final String DYNAMIC_DELETE = HOST_CN +"circle/media/deleteMedia?"+TOKEN_KEY+"=";

    /*动态：添加黑名单*/
    public static final String DYNAMIC_ADD_BLACKLIST = HOST_CN +"circle/mediaShield/save?"+TOKEN_KEY+"=";

    /*商品分类*/
    public static final String GOODS_CATEGORY = HOST_CN +"shop/cate/query?"+TOKEN_KEY+"=";

    /*商品分类品牌下的接口*/
    public static final String GOODS_CHILD_CATEGORY = HOST_CN +"shop/cate/brand/query?"+TOKEN_KEY+"=";

    /*商品轮播图*/
    public static final String GOODS_BANNER = "shop/banner/query?"+TOKEN_KEY+"=";

    /*分类商品列表*/
    public static final String CATEGORY_GOODS_LIST = HOST_CN +"shop/goods/page/query?"+TOKEN_KEY+"=";

    /*品牌列表*/
    public static final String BRANDS = HOST_CN +"shop/store/goodsBrand?"+TOKEN_KEY+"=";

    /*商品筛选属性名列表*/
    public static final String SKU_ATTRIBUTE = HOST_CN +"shop/store/getGoodsSku?"+TOKEN_KEY+"=";

    /*商品筛选属性值列表*/
    public static final String SKU_ATTRIBUTE_VALUE = HOST_CN +"shop/store/getSkuItem?"+TOKEN_KEY+"=";

    /*店铺信息*/
    public static final String SHOP_INFO = HOST_CN +"shop/store/info?"+TOKEN_KEY+"=";

    /*添加购物车*/
    public static final String ADD_SHOPPING_CART  = HOST_CN +"shop/cart/add?"+TOKEN_KEY+"=";

    /*商品收藏*/
    public static final String GOODS_COLLECT = HOST_CN +"shop/goods/fav/add?"+TOKEN_KEY+"=";

    /*密码登录*/
    public static final String LOGIN_PSW = HOST_CN +"system/user/mobile/loginPassword";

    /*发送验证码*/
    public static final String SEND_VERIFICATION_CODE = HOST_CN +"system/user/mobile/code";

    /*修改密码*/
    public static final String SET_PSW = HOST_CN +"system/user/mobile/changePassword";

    /*验证码登录*/
    public static final String LOGIN_CODE = HOST_CN +"system/user/mobile/login";

    /*产品分享*/
    @Nullable
    public static final String GOODS_SHARE = "http://www.dmbg.net/shareApp.html?goodsId=";

    @NotNull
    public static final String CLIQUE_MEDIA_LIST = "circle/media/page/query/referrer?"+TOKEN_KEY+"=";

}
