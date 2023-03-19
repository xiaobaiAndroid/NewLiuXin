package module.common.utils;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import module.common.R;
import module.common.data.entity.Goods;
import module.common.data.entity.GoodsDetailSku;
import module.common.data.entity.GoodsSkuAttributeValue;
import module.common.data.entity.UserInfo;
import module.common.data.request.CreateOrderReq;

/**
 * @describe: ARouter帮助类
 * @date: 2020/1/1
 * @author: Mr Bai
 */
public class ARouterHelper {


    public static final String VIDEO_DETAIL = "/video/VideoDetailActivity";
    public static final String MODIFY_INTRO = "/user/ModifyIntroActivity";
    public static final String MODIFY_JOB = "/user/ModifyJobActivity";
    public static final String MY_QRCODE = "/user/MyQRCodeActivity";
    public static final String MY_INFORMATION = "/user/MyInformationActivity";
    public static final String SCAN_QRCODE = "/qrcode/ScanQRCodeActivity";


    public static final String CREATE_TEAM = "/im/CreateTeamActivity";

    public static final String FRA_SHOP_HOME = "/goods/ShopHomeFragment";
    public static final String FRA_MY = "/user/MyFragment";
    public static final String FRA_NEARBY_LIST = "/store/NearbyListFragment";
    public static final String FRA_CLIQUE_HOME = "/clique/CliqueHomeFragment";

    public static final String ADD_SEARCH_FRIEND = "/im/SearchAddFriendActivity";
    public static final String ADDRESSBOOK_SEARCH = "/im/AddressBookSearchActivity";
    public static final String FRIEND_GROUPING = "/im/FriendGroupingActivity";
    public static final String IM_USER_SETUP = "/im/UserSetupActivity";

    public static final String ADDRESSBOOK_HOME = "/im/AddressBookHomeActivity";

    //账单列表
    public static final String BILL_LIST = "/wallet/BilListActivity";

    public static final String APPLY_STORE = "/store/ApplyStoreActivity";
    public static final String STORE_INFO = "/store/StoreDetailActivity";


    public static final String EARNINGS_BILL_LIST = "/wallet/EarningBillActivity";
    /*相册详情*/
    public static final String ALBUM_DETAIL = "/album/DetailActivity";
    /*新建相册*/
    public static final String ALBUM_ADD = "/album/AddActivity";
    /*修改昵称*/
    public static final String MODIFY_NICKNAME = "/user/ModifyNicknameActivity";

    /*订单详情*/
    public static final String ORDER_DETAIL = "/order/OrderDetailActivity";
    /*订单列表*/
    public static final String ORDER_LIST = "/order/OrderListActivity";

    /*商城商品结算页面*/
    public static final String GOODS_ORDER_SETTLE = "/goods/SettleActivity";


    /*地址列表*/
    public static final String ADDRESSES = "/address/AddressesActivity";

    /*添加地址*/
    public static final String ADD_ADDRESS = "/address/AddAddressActivity";

    /*支付结果页面*/
    public static final String PAY_RESULT = "/pay/PayResultActivity";
    /*支付页面*/
    public static final String PAY = "/pay/PayActivity";

    /*相册主界面*/
    public static final String ALBUM_HOME = "/album/HomeActivity";
    /*发布主界面*/
    public static final String PUBLISH_HOME = "/publish/PublishHomeActivity";
    public static final String PUBLISH_ENTRANCE = "/publish/PublishEntranceActivity";

    /*视频编辑*/
    public static final String VIDEO_MODIFY = "/publish/VideoModifyActivity";

    /*发布*/
    public static final String PUBLISH = "/publish/PublishActivity";

    /*位置列表*/
    public static final String LOCATIONS = "/map/LocationsActivity";

    /*地图*/
    public static final String MAP = "/map/MapActivity";

    /*视频录制*/
    public static final String VIDEO_RECORD = "/publish/VideoRecordActivity";

    /*图文编辑*/
    public static final String IMGTEXT_MODIFY = "/publish/ImgtextModifyActivity";

    /*图文详情*/
    public static final String IMGTXT_DETAIL = "/clique/ImgTxtDetailActivity";

    /*视频页面*/
    public static final String VIDEO = "/clique/VideoActivity";

    /*我的生活圈：搜索*/
    public static final String DYNAMIC_SEARCH = "/clique/SearchActivity";

    /*商品详情*/
//    public static final String GOODS_DETAIL = "/app/GoodsActivity";
    public static final String GOODS_DETAIL = "/goods/GoodsActivity";


    /*我的订单*/
    public static final String MY_ORDER = "/app/MyOrdersActivity";


    /*设置*/
    public static final String SETTING = "/user/SettingActivity";

    /*我的的卡片*/
    public static final String MY_CARD = "/app/MyCardActivity";

    /*单聊用户设置页面*/
    public static final String SINGLE_CHAT_SET = "/app/SingleChatSetActivity";


    /*我的生活圈*/
    public static final String MY_CLIQUE = "/user/MyCliqueActivity";

    /*我的收藏*/
    public static final String MY_COLLECT = "/user/MyCollectActivity";

    /*收藏的商品*/
    public static final String COLLECT_GOODS = "/user/CollectGoodsActivity";

    /*图片编辑*/
    public static final String PICTURE_COMPILE = "/picture/CompileActivity";

    /*音乐库*/
    public static final String MUSIC_LIBRARY = "/music/LibraryActivity";

    /*音乐分类详情列表*/
    public static final String MUSIC_LIST = "/music/MusicListActivity";

    /*选择城市列表*/
    public static final String CHOOSE_CITY = "/map/ChooseCityActivity";

    /*充值*/
    public static final String RECHARGE = "/pay/RechargeActivity";

    /*充值协议*/
    public static final String RECHARGE_PROTOCOL = "/web/ProtocolActivity";

    /*排行榜*/
    public static final String RANKING = "/clique/RankingActivity";

    /*排行列表*/
    public static final String RANKING_LIST_HOME = "/clique/ListHomeActivity";

    /*搜索好友*/
    public static final String SEARCH_FRIEND = "/app/SearchFriendActivity";

    /*发起群聊*/
    public static final String ADD_TEAM = "/app/AddTeamActivity";

    /*联系人列表主页*/
    public static final String CONTACTS_HOME = "/app/AddressBookActivity";

    /*全局搜索*/
    public static final String GLOBAL_SEARCH = "/im/GlobalSearchActivity";

    /*单聊*/
    public static final String SINGLE_CHAT = "/im/SingleChatActivity";

    /*群聊*/
    public static final String TEAM_CHAT = "/im/TeamChatActivity";

    /*发红包*/
    public static final String SEND_REDPACKET = "/im/SendRedPacketActivity";

    /*好友列表*/
    public static final String FRIENDS = "/im/FriendsActivity";

    /*红包详情*/
    public static final String RP_DETAIL = "/im/RPDetailActivity";

    /*IM用户信息主页*/
    public static final String IM_USERINFO_HOME = "/im/UserHomeActivity";

    /*身份证认证*/
    public static final String IDENTITY_CARD_CERTIFICATION = "/user/CertificationActivity";

    /*商品搜索*/
    public static final String GOODS_SEARCH = "/goods/SearchHomeActivity";

    /*搜索结果*/
    public static final String GOODS_SEARCH_RESULT = "/goods/SearchResultActivity";

    /*品牌列表*/
    public static final String BRANDS = "/goods/BrandListActivity";

    /*商品分类列表*/
    public static final String GOODS_CATEGORIES = "/app/CateActivity";

    /*店铺详情*/
    public static final String SHOP_DETAIL = "/app/StoreActivity";

    /*订单结算页面*/
    public static final String ORDER_SETTLEMENT = "/app/OrderSettlementActivity";

    /*购物车*/
    public static final String SHOPPING_CART = "/shoppingcart/CartActivity";

    /*登录*/
    public static final String LOGIN_PSW = "/login/LoginPswActivity";


    /*首页*/
    public static final String MAIN = "/main/MainActivity";

    /*设置密码*/
    public static final String SET_PSW = "/user/SetPswActivity";

    /*注册*/
    public static final String REGISTER = "/user/ReigsterActivity";


    @NotNull
    public static Fragment getFragment(@NotNull String fragmentUri) {
        return (Fragment) ARouter.getInstance().build(fragmentUri).navigation();
    }


    /**
     * @describe: 打开指定路径的页面
     * @date: 2020/1/2
     */
    public static void openPath(Context context,String path, Bundle bundle){
        ARouter.getInstance().build(path)
                .with(bundle)
//                .withTransition(R.anim.activity_enter,R.anim.activity_exit)
                .navigation(context);
    }

//    /**
//     * @describe: 打开指定路径的页面
//     * @date: 2020/1/2
//     */
//    public static void openPath(String path){
//        ARouter.getInstance().build(path)
//                .withTransition(R.anim.activity_enter,R.anim.activity_exit)
//                .navigation();
//    }

    /**
     * @describe: 打开指定路径的页面
     * @date: 2020/1/2
     */
    public static void openPath(Context context, String path){
        ARouter.getInstance().build(path)
//                .withTransition(R.anim.activity_enter,R.anim.activity_exit)
                .navigation(context);
    }


    /**
     * @describe: 打开指定路径的页面
     * @date: 2020/1/2
     */
    public static void openBottomToTop(Context context, String path){
        ARouter.getInstance().build(path)
                .withTransition(R.anim.enter_bottom_top, R.anim.exit_top_bottom)
                .navigation(context);
    }

    /**
     * @describe: 打开指定路径的页面
     * @date: 2020/1/2
     */
    public static void openBottomToTop(Context context, String path,Bundle bundle){
        ARouter.getInstance().build(path)
                .with(bundle)
                .withTransition(R.anim.enter_bottom_top, R.anim.exit_top_bottom)
                .navigation(context);
    }

    public static void toGoodsDetail(Context context,String goodsId,String actId) {
        Bundle bundle = new Bundle();
        bundle.putString(Key.PARAMS_GOODS_ID,goodsId);
        bundle.putString(Key.PARAMS_ACT_ID,actId);
        ARouterHelper.openPath(context,ARouterHelper.GOODS_DETAIL,bundle);
    }

    public static void toShopDetail(Context context, @Nullable String storeId) {
        Bundle bundle = new Bundle();
        bundle.putString(Key.STORE_ID,storeId);
       openPath(context,ARouterHelper.SHOP_DETAIL,bundle);
    }

    /**
     * @describe: 打开订单结算页面
     * @date: 2020/5/5
     */
    public static void toOrderSettlement(Context context, ArrayList<GoodsSkuAttributeValue>  selectedSkuValues,@Nullable GoodsDetailSku selectedSku, int buyNumber, @Nullable Goods goods) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Key.SKU,selectedSku);
        bundle.putParcelable(Key.GOODS,goods);
        bundle.putParcelableArrayList(Key.SKU_VALUES,selectedSkuValues);
        bundle.putInt(Key.NUMBER,buyNumber);
        openPath(context,ARouterHelper.ORDER_SETTLEMENT,bundle);
    }

    /**
     * @describe: 打开商品搜索结果页面
     * @date: 2020/5/6
     */
    public static void toGoodsSearchResult(Context context, String cateId, String keyword) {
        Bundle bundle = new Bundle();
        if(!"0".equals(cateId)){
            bundle.putString(Key.CATE_ID,cateId);
        }
        bundle.putString(Key.KEY_WORD,keyword);
        openPath(context,ARouterHelper.GOODS_SEARCH_RESULT,bundle);
    }

    /**
     * @describe: 打开订单结算页面
     * @date: 2020/5/5
     */
    public static void toOrderSettlement(Context context, CreateOrderReq req) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Key.ENTITY,req);
        openPath(context,ARouterHelper.GOODS_ORDER_SETTLE,bundle);
    }

    /*
    * @describe: 账单列表
    * @date: 12/11/20
    */
    public static void toBillList(Context context, @Nullable String accountId, @NotNull String title, boolean isEarnings) {
        Bundle bundle = new Bundle();
        bundle.putString(Key.USERID,accountId);
        bundle.putString(Key.KEY_WORD,title);
        bundle.putBoolean("isEarnings",isEarnings);
        openPath(context,ARouterHelper.BILL_LIST,bundle);
    }

    public static void toEarningsBillList(@NotNull Context context, @Nullable String accountId, @NotNull String title) {
        Bundle bundle = new Bundle();
        bundle.putString(Key.USERID,accountId);
        bundle.putString(Key.KEY_WORD,title);
        openPath(context,ARouterHelper.EARNINGS_BILL_LIST,bundle);
    }

    public static void toIMUserHome(@NotNull Context context, @Nullable String userId){
        Bundle bundle = new Bundle();
        bundle.putString(Key.USERID, userId);
        openPath(context,IM_USERINFO_HOME,bundle);
    }

    public static void toIMUserSetup(@NotNull Context context, @Nullable UserInfo userInfo) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Key.ENTITY, userInfo);
        openPath(context,IM_USER_SETUP,bundle);
    }

    /*
    * @describe: 群聊
    * @date: 12/30/20
    */
    public static void toTeamChat(@NotNull Context context, @Nullable String teamId) {
        Bundle bundle = new Bundle();
        bundle.putString(Key.ID, teamId);
        openPath(context,TEAM_CHAT,bundle);
    }

    public static class Key{
        /*商品id*/
        public static final String PARAMS_GOODS_ID = "goodsId";


        /*搜索关键字*/
        public static final String KEY_WORD = "keyword";

        @Nullable
        public static final String PATH = "path";
        @Nullable
        public static final String POSITION = "position";
        @Nullable
        public static final String CATEGORY = "category";
        public static final String ID = "id";


        public static final String REDPACKET = "redpacket";
        @Nullable
        public static final String IS_SELF = "isSelf";
        @Nullable
        public static final String USERID = "userId";
        @Nullable
        public static final String NICKNAME = "nickname";
        @Nullable
        public static final String AVATAR = "avatar";

        /*IM发送收藏的操作*/
        @Nullable
        public static final String IM_SEND_COLLECT = "im_send_collect";

        /*查看地图*/
        @Nullable
        public static final String LOOK_MAP = "lookMap";
        @Nullable
        public static final String LOCAION = "location";
        @Nullable
        public static final String IS_SINGLE = "isSingle";

        public static final String TYPE = "type";
        @Nullable
        public static final String MONEY = "money";

        public static final String GIFT_READ = "giftRead";

        public static final String IS_PLAY = "isPlay";
        @Nullable
        public static final String PROTOCOL_TYPE = "protocol_type";
        @Nullable
        public static final String PARAMS_ACT_ID="actId";

        public static final String STORE_ID = "storeId";

        public static final String SKU = "sku";
        public static final String GOODS = "goods";
        public static final String NUMBER = "number";
        public static final String SKU_VALUES = "skuValues";
        public static final String CATE_ID = "cateId";
        @Nullable
        public static final String PAY_ORDER = "payOrder";
        @Nullable
        public static final String PAYINFO = "payInfo";

        @Nullable
        public static final String ENTITY = "entity";
        @Nullable
        public static final String SELECT = "select";
        public static final String ADDRESS = "address";
        public static final String STATUS = "status";
        @Nullable
        public static final String STORE = "store";
    }

}
