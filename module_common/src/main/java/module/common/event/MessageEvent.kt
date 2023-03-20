package module.common.event

/**
 * @describe: EventBuss消息体
 * @date: 2020/1/1
 * @author: Mr Bai
 */
class MessageEvent(var type: Type) {
    var obj: Any? = null

   enum class Type{
       RELEASE,
       //滤镜
       FILTER,
       //刷新
       REFRESH,
       REFRESH_ALL,
       //屏幕方向
       SCREEN_ORIENTATION,
       //添加贴图
       STICKER_ADD,
       SAVE,
       //融合处理的图片的结果
       CONCAT_BITMAP_RESULT,

       UPDATE_USERINFO,

       MUSIC_SELECT_MUSIC,
       EXIT_APP,
       PUBLISH_SELECT_CATEGORY,
       MAIN_GOODS_PAGE,
       //选择标签
       SELECT_LABEL,
       SELECTED_CITY,
       SHOW_WAY,
       DYNAMIC_SELECT_CATEGORY,
       PLAY_VIDEO_SVGA,
       DELETE_DYNAMIC,
       SEND_COMMENT
   }

}