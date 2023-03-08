package module.common.event;

import org.jetbrains.annotations.Nullable;

/**
 * @describe: EventBuss消息体
 * @date: 2020/1/1
 * @author: Mr Bai
 */
public class MessageEvent {

    private int type;
    private Object obj;

    public MessageEvent(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static class MessageType {

        /*列表显示方式*/
        public static final int SHOW_WAY = 1;

        /*更新用户信息*/
        public static final int UPDATE_USERINFO = 2;

        /*视频初始化完成是否开始播放*/
        public static final int VIDEO_START_PLAY = 3;

        /*播放视频页面的礼物动画*/
        public static final int PLAY_VIDEO_SVGA = 4;

        /*显示礼物数量的PopupView*/
        public static final int SHOW_GIFT_NUMBER_POPUP = 5;

        /*发送评论*/
        public static final int SEND_COMMENT = 6;

        /*上传文件*/
        public static final int UPLOAD_FILE = 7;

        /*选择的位置信息*/
        public static final int SELECT_LOCATION = 8;

        /*发布页选择分类*/
        public static final int PUBLISH_SELECT_CATEGORY = 9;

        /*发布 截取视频第一帧*/
        public static final int SNAPSHOT = 10;

        /*转码进度*/
        public static final int VIDEO_TRANSCODING_PROGRESS = 11;

        /*开始发布*/
        public static final int START_PUBLISH  =12;

        /*生活圈：选择分类*/
        public static final int CLIQUE_SELECT_CATEGORY = 13;

        /*图片编辑：修改滤镜*/
        public static final int PICTURE_MODIFY_FILTER = 14;

        /*图片编辑：加载滤镜*/
        public static final int LOAD_FILTER = 15;

        /*图片编辑：位置改变*/
        public static final int PICTURE_POSITION_CHANGE = 16;

        /*音乐库：选择的音乐*/
        public static final int MUSIC_SELECT_MUSIC  =17;

        /*确认选择音乐*/
        public static final int MUSIC_CONFIRM_SELECT = 18;

        /*发送红包*/
        public static final int SEND_RED_PACKET = 19;

        /*发送个人名片*/
        public static final int SEND_BUSINESS_CARD = 20;

        /*发送收藏*/
        public static final int SEND_COLLECT = 21;

        /*领取红包状态*/
        public static final int GET_RED_PACKET = 22;

        /*选择贴纸*/
        public static final int PICTURE_SELECT_PASTER  =23;

        /*选择的城市*/
        public static final int SELECTED_CITY = 24;

        /*更新浏币数量*/
        public static final int UPDATE_MONEY_COUNT = 25;

        /*修改在线状态*/
        public static final int MODIFY_ONLINE_STATUS = 26;

        /*提交身份证认证*/
        public static final int SUBMIT_IDENTITY_CARD = 27;

        /*删除动态*/
        public static final int DELETE_DYNAMIC = 28;

        /*选中的品牌*/
        public static final int SELCTC_BRANDS = 29;
        @Nullable
        public static final int SELECT_ADDRESS = 33;

        /*刷新地址列表*/
        public static final int REFRESH_ADDRESS_LIST = 34;

        /*刷新订单列表*/
        public static final int REFRESH_ORDER_LIST = 35;

        /*刷新相册列表*/
        public static final int REFRESH_ALBUMS = 36;

        //刷新系统用户信息
        public static final int REFRESH_SYS_USERINFO = 37;
        public static final int EXIT_APP = 38;
        public static final int UPDATE_IM_USER_REMARK = 39;
        public static final int REFRESH_IM_USER_INFO = 40;

        public static final int SAME_CITY_USER_FILTRATE = 41;
        public static final int SAME_CITY_TEAM_FILTRATE = 42;
        public static final int REFRESH_FRIEND_LIST = 43;
        public static final int MAIN_GOODS_PAGE = 44;
                ;
    }
}
