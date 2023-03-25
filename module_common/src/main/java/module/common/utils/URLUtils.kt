package module.common.utils

/**
 * @describe: 请求链接工具类
 * @date: 2020/1/10
 * @author: Mr Bai
 */
object URLUtils {


    //中文
    const val HOST_CN = "http://tst.api.yiguo.fun/"

    //英文
    const val HOST_EN = "http://tst.api.yiguo.fun/"
    var TOKEN_KEY = "access_token"
    val USER_CERFICATION = "system/user/getUserCertification?" + TOKEN_KEY + "="
    val MY_QRCODE = "system/my/barcode?" + TOKEN_KEY + "="
    val IM_CREATE_TEAM = "system/user/im/team/create?" + TOKEN_KEY + "="
    val IM_DEL_GROUPING = "system/user/im/group/del?" + TOKEN_KEY + "="
    val IM_ADD_GROUPING = "system/user/im/group/save?" + TOKEN_KEY + "="

    /*分组*/
    val IM_GROUPING_LIST = "system/user/im/group/query?" + TOKEN_KEY + "="
    val IM_USER_LIST = "system/user/im/query?" + TOKEN_KEY + "="

    /*我加入的群列表*/
    val IM_MY_TEAM_LIST = "system/user/im/my/team?" + TOKEN_KEY + "="
    val IM_SAME_CITY_TEAM_LIST = "system/user/im/city/team/query?" + TOKEN_KEY + "="
    val IM_SAME_CITY_USER_LIST = "system/user/im/city/query?" + TOKEN_KEY + "="

    //删除好友
    val IM_DELETE_FRIEND = "system/user/im/friend/del?" + TOKEN_KEY + "="
    val IM_ADD_BLACKLIST = "system/user/im/friend/to/black?" + TOKEN_KEY + "="
    val IM_UPDATE_USER_GROUP = "system/user/im/friend/update?" + TOKEN_KEY + "="
    val IM_CONTACTS_GROUP_LIST = "system/user/im/friend/query?" + TOKEN_KEY + "="
    val STORE_LIST = "system/store/page/query?" + TOKEN_KEY + "="

    //申请店铺
    val APPLY_STORE = "system/store/merchant/create?" + TOKEN_KEY + "="

    //后台系统用户信息
    val SYSTEM_USER_INFO = "system/admin/user/sys/info?" + TOKEN_KEY + "="

    //账单数据
    val BILL_LIST = "shop/account/page/query?" + TOKEN_KEY + "="

    /*更新扩展的用户信息*/
    val UPDATE_EXTEND_USER_INFO = "system/user/extend/update?" + TOKEN_KEY + "="

    /*扩展的用户信息*/
    val EXTEND_USER_INFO = "system/user/extend/info?" + TOKEN_KEY + "="

    /*相册：更新目录*/
    val ALBUM_UPDATE_DIR =
        HOST_CN + "system/admin/merchant/updateMerchantFileFolder?" + TOKEN_KEY + "="

    /*相册：图片列表*/
    val ALBUM_PICTURES =
        HOST_CN + "system/admin/merchant/page/queryMerchantImage?" + TOKEN_KEY + "="

    /*相册：添加图片*/
    val ALBUM_ADD_PICTURE = HOST_CN + "system/admin/merchant/createMerchantImage?" + TOKEN_KEY + "="

    /*是否是商户账号*/
    val IS_MERCH = HOST_CN + "system/admin/privilege/user/store?" + TOKEN_KEY + "="

    /*新建相册*/
    val ALBUM_ADD_DIR =
        HOST_CN + "system/admin/merchant/createMerchantFileFolder?" + TOKEN_KEY + "="

    /*后台用户信息*/
    val BACKSTAGE_USER_INFO = HOST_CN + "system/admin/user/sys/info?" + TOKEN_KEY + "="

    /*注册*/
    const val REGISTER = HOST_CN + "system/user/mobile/register"

    /*支付*/
    val PAY = HOST_CN + "shop/order/pay?" + TOKEN_KEY + "="

    /*获取收货地址列表*/
    val ADDRESS_LIST = HOST_CN + "system/user/shipping/query?" + TOKEN_KEY + "="

    /*保存收货地址*/
    val SAVE_ADDRESS = HOST_CN + "system/user/shipping/save?" + TOKEN_KEY + "="

    /*删除地址*/
    val DELETE_ADDRESS = HOST_CN + "system/user/shipping/del?" + TOKEN_KEY + "="

    /*购物车所有数据*/
    val ALL_SHOPPING_CART = HOST_CN + "shop/cart?" + TOKEN_KEY + "="

    /*更新购物车数量*/
    val UPDATE_SHOPPINGCART_NUMBER = HOST_CN + "shop/cart/update?" + TOKEN_KEY + "="

    /*删除购物车商品*/
    val DELETE_SHOPPINGCART = HOST_CN + "shop/cart/del?" + TOKEN_KEY + "="

    /*删除订单*/
    val ORDER_DELETE = HOST_CN + "shop/order/shipping/deleteOrder?" + TOKEN_KEY + "="

    /*取消订单*/
    val ORDER_CANCEL = HOST_CN + "shop/order/shipping/cancleOrder?" + TOKEN_KEY + "="

    /*订单详情*/
    val ORDER_DETAIL = HOST_CN + "shop/order/info?" + TOKEN_KEY + "="

    /*订单列表实体*/
    val ORDER_LIST = HOST_CN + "shop/order/page/query?" + TOKEN_KEY + "="

    /*下单*/
    val CREATE_ORDER = HOST_CN + "shop/cart/order?" + TOKEN_KEY + "="

    /*获取默认收货人信息*/
    val DEFAULT_USER_SHIPPING = HOST_CN + "system/user/shipping/query?" + TOKEN_KEY + "="

    /*账户余额*/
    val BALANCE = HOST_CN + "shop/account/user/info?" + TOKEN_KEY + "="

    /*获取订单支付信息*/
    val ORDER_PAY_INFO = HOST_CN + "shop/order/pay/info?" + TOKEN_KEY + "="

    /*相册目录列表*/
    val ALBUM_DIR =
        HOST_CN + "system/admin/merchant/page/queryMerchantFileFolder?" + TOKEN_KEY + "="

    /*店铺商品列表*/
    val SHOP_GOODS = "shop/store/goods/page/query?" + TOKEN_KEY + "="

    /*获取生活圈分类*/
    val CLIQUE_CATEGORY = HOST_CN + "circle/mediaType/typeQuery?" + TOKEN_KEY + "="

    /*生活圈，推荐列表*/
    val DYNAMIC_RECOMMEND_LIST = HOST_CN + "circle/media/page/query/referrer?" + TOKEN_KEY + "="

    /*生活圈，好友列表*/
    val DYNAMIC_FRIEND_LIST = HOST_CN + "circle/media/page/query/friend?" + TOKEN_KEY + "="

    /*生活圈，同城列表*/
    val DYNAMIC_CITY_LIST = HOST_CN + "circle/media/page/query/city?" + TOKEN_KEY + "="

    /*生活圈，其他列表*/
    val DYNAMIC_OTHER_LIST = HOST_CN + "circle/media/page/query/typeId?" + TOKEN_KEY + "="

    /*礼物列表*/
    val GIFT_CATEGORY = HOST_CN + "system/gold/gift/giftType?" + TOKEN_KEY + "="

    /*根据分类id获取礼物列表*/
    val GIFT_LIST = HOST_CN + "system/gold/gift/page/query?" + TOKEN_KEY + "="

    /*钱包，账户余额*/
    val WALLET = HOST_CN + "shop/gold/account/user/info?" + TOKEN_KEY + "="

    /*赠送礼物*/
    val GIVE_GIFT = HOST_CN + "system/gold/gift/sendGift?" + TOKEN_KEY + "="

    /*点赞*/
    val ENDORSE = "circle/mediaPraise/praise?" + TOKEN_KEY + "="

    /*动态收藏*/
    val COLLECT_DYNAMIC = "circle/mediaFav/favorite?" + TOKEN_KEY + "="

    /*获取关注状态*/
    val ATTENTION_STATUS = "circle/mediaLike/getLikeStatus?" + TOKEN_KEY + "="

    /*关注*/
    val ATTENTION = "circle/mediaLike/like?" + TOKEN_KEY + "="

    /*关注用户*/
    val ATTENTION_USER = "circle/mediaLike/like?" + TOKEN_KEY + "="

    /*评论列表*/
    val COMMENT_LIST = HOST_CN + "circle/mediaComment/getCommentPage?" + TOKEN_KEY + "="

    /*发表评论*/
    val PUBLISH_COMMENT = HOST_CN + "circle/mediaComment/comment?" + TOKEN_KEY + "="

    /*获取上传秘钥*/
    val UPLOAD_SIGN = HOST_CN + "system/upload/sign?" + TOKEN_KEY + "="

    /*发布动态*/
    val PUBLISH_DYNAMIC = HOST_CN + "circle/media/lifeCircleRelease?" + TOKEN_KEY + "="

    /*推荐商品*/
    val RECOMMEND_GOODS = HOST_CN + "shop/goods/page/querySell?" + TOKEN_KEY + "="

    /*刷新token*/
    const val REFRESH_TOKEN = "system/user/token/refresh"

    /*我的动态数据*/
    val MY_DYNAMIC = "circle/myMedia/getMyMediaData?" + TOKEN_KEY + "="

    /*我的评论列表*/
    val MY_COMMENT = HOST_CN + "circle/myMedia/getCommentData?" + TOKEN_KEY + "="

    /*我的生活圈：赞列表*/
    val MY_ENDORSES = HOST_CN + "circle/myMedia/getPraiseData?" + TOKEN_KEY + "="

    /*打赏给我礼物*/
    val MY_GIFTS = HOST_CN + "circle/myMedia/getGiftData?" + TOKEN_KEY + "="

    /*动态搜索*/
    val DYNAMIC_SEARCH = HOST_CN + "circle/media/page/query/titleLike?" + TOKEN_KEY + "="

    /*音乐类别*/ //    http://tst.api.yiguo.fun/circle/mediaMusic/typeQuery?access_token=eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIzNTk2MDMyMjE5MDk4NzY3MzYiLCJhdWQiOiJBQ0NFU1NfVE9LRU4iLCJpYXQiOjE2NzgzMjQwNjcsInN1YiI6IjEzNTM5NzEzOTU1IiwiZXhwIjoxNjc4MzUyODY3fQ.aNOlPnKcfvsicqKkXd9ZAHs9PJpBnMfcf2fjeCASb5Q
    val MUSIC_CATEGORY = HOST_CN + "circle/mediaMusic/typeQuery?" + TOKEN_KEY + "="

    /*更加类别获取音乐*/
    val MUSICS_BY_CATEGORY = HOST_CN + "circle/mediaMusic/musicPageByType?" + TOKEN_KEY + "="

    /*联系人资料*/
    val CONTACTS = HOST_CN + "system/user/extend/info?" + TOKEN_KEY + "="

    /*发送红包*/
    val SEND_RED_PACKET = HOST_CN + "system/gold/redPacket/sendRedPacket?" + TOKEN_KEY + "="

    /*获取好友列表*/
    val FRIENDS = HOST_CN + "system/user/im/friend/query?" + TOKEN_KEY + "="

    /*领取红包*/
    val GET_RED_PACKET = HOST_CN + "system/gold/redPacket/openRedPacket?" + TOKEN_KEY + "="

    /*收藏的商品*/
    val COLLECT_GOODS = HOST_CN + "shop/goods/fav/page/query?" + TOKEN_KEY + "="

    /*收藏的动态列表*/
    val COLLECT_DYNAMICIE_LIST = HOST_CN + "circle/myMedia/page/query/favorite?" + TOKEN_KEY + "="

    /*商品详情*/
    val GOODS_DETAIL = HOST_CN + "shop/goods/info?" + TOKEN_KEY + "="

    /*获取省地址*/
    val ADDRESS = HOST_CN + "system/address/query?" + TOKEN_KEY + "="

    /*充值协议*/
    const val PROTOCOL = HOST_CN + "system/protocol/info"

    /*充值数据列表*/
    val RECHARGE_DATA = HOST_CN + "system/gold/topUp/query?" + TOKEN_KEY + "="

    /*创建充值订单*/
    val RECHARGE_ORDER = HOST_CN + "system/gold/topUpOrder?" + TOKEN_KEY + "="

    /*浏币充值支付*/
    val VIRTUAL_PAY = HOST_CN + "system/gold//ali/topUp?" + TOKEN_KEY + "="

    /*浏览排行*/
    val LOOK_RANKING = HOST_CN + "circle/rankings/page/mediaByViewNum?" + TOKEN_KEY + "="

    /*富豪排行榜*/
    val RICH_RANKING = HOST_CN + "circle/rankings/page/richList?" + TOKEN_KEY + "="

    /*明星作品排行*/
    val STAR_WORK_RANKING = HOST_CN + "circle/rankings/page/starsMediaList?" + TOKEN_KEY + "="

    /*点赞排行榜*/
    val ENDORSE_RANKING = HOST_CN + "circle/rankings/page/praiseList?" + TOKEN_KEY + "="

    /*好友请求列表*/
    val FRIEND_REQUEST = HOST_CN + "system/user/im/operation/query?" + TOKEN_KEY + "="

    /*通过好友请求*/
    val FRIEND_PASS_REQUEST = HOST_CN + "system/user/im/operation/sure?" + TOKEN_KEY + "="

    /*修改用户信息*/
    val MODIFY_USER_INFO = "system/user/extend/update?" + TOKEN_KEY + "="

    /*获取用户认证信息*/
    val USER_CERTIFICATION_INFO = HOST_CN + "system/user/getUserCertification?" + TOKEN_KEY + "="

    /*身份证认证*/
    val SUBMIT_IDENTITY_CARD = HOST_CN + "system/tencentapi/OCRIdCard?" + TOKEN_KEY + "="

    /*动态删除*/
    val DYNAMIC_DELETE = HOST_CN + "circle/media/deleteMedia?" + TOKEN_KEY + "="

    /*动态：添加黑名单*/
    val DYNAMIC_ADD_BLACKLIST = HOST_CN + "circle/mediaShield/save?" + TOKEN_KEY + "="

    /*商品分类*/
    val GOODS_CATEGORY = HOST_CN + "shop/cate/query?" + TOKEN_KEY + "="

    /*商品分类品牌下的接口*/
    val GOODS_CHILD_CATEGORY = HOST_CN + "shop/cate/brand/query?" + TOKEN_KEY + "="

    /*商品轮播图*/
    val GOODS_BANNER = "shop/banner/query?" + TOKEN_KEY + "="

    /*分类商品列表*/
    val CATEGORY_GOODS_LIST = HOST_CN + "shop/goods/page/query?" + TOKEN_KEY + "="

    /*品牌列表*/
    val BRANDS = HOST_CN + "shop/store/goodsBrand?" + TOKEN_KEY + "="

    /*商品筛选属性名列表*/
    val SKU_ATTRIBUTE = HOST_CN + "shop/store/getGoodsSku?" + TOKEN_KEY + "="

    /*商品筛选属性值列表*/
    val SKU_ATTRIBUTE_VALUE = HOST_CN + "shop/store/getSkuItem?" + TOKEN_KEY + "="

    /*店铺信息*/
    val SHOP_INFO = HOST_CN + "shop/store/info?" + TOKEN_KEY + "="

    /*添加购物车*/
    val ADD_SHOPPING_CART = HOST_CN + "shop/cart/add?" + TOKEN_KEY + "="

    /*商品收藏*/
    val GOODS_COLLECT = HOST_CN + "shop/goods/fav/add?" + TOKEN_KEY + "="

    /*密码登录*/
    const val LOGIN_PSW = HOST_CN + "system/user/mobile/loginPassword"

    /*发送验证码*/
    const val SEND_VERIFICATION_CODE = HOST_CN + "system/user/mobile/code"

    /*修改密码*/
    const val SET_PSW = HOST_CN + "system/user/mobile/changePassword"

    /*验证码登录*/
    const val LOGIN_CODE = HOST_CN + "system/user/mobile/login"

    val IMGTXT_DATA = "circle/mediaComment/getImageTextData?$TOKEN_KEY="


    /*产品分享*/
    val GOODS_SHARE: String? = "http://www.dmbg.net/shareApp.html?goodsId="
    val CLIQUE_MEDIA_LIST = "circle/media/page/query/referrer?$TOKEN_KEY="
}