package module.common.data.request;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe: 发布请求
 * @date: 2020/3/13
 * @author: Mr Bai
 */
public class PublishReq {


    /**
     * type : 1
     * typeId : 5
     * title : 布瓜
     * description : 🉑️的
     * cityCode : null
     * lat : null
     * lng : null
     * position : null
     * mediaSource : [{"url":"https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2019/11/%E5%A5%B6.png","mediaImageTag":[{"tagX":"110","tagY":"120","tagComment":"这是一个标签"},{"tagX":"180","tagY":"190","tagComment":"这是两个标签"}]}]
     * mediaStatus : 0
     * viewFriendList : [342900023430098944,342900023430098945]
     * goodsId :
     * musicUrl :
     * coverUrl : wwwwwwwwwwwwwwwww1111
     */

    /*	媒体类型：1-音图文、2-IN视频*/
    private String type;
    /*分类ID*/
    private String typeId;
    private String title;
    private String description;
    /*城市编码*/
    private String cityCode;
    private String lat;
    private String lng;
    private String position;

    /*	权限状态（公开/私有）0公开，1私有*/
    private String mediaStatus = "0";
    private String goodsId="";
    private String musicUrl="";
    /*封面图*/
    private String coverUrl;
    private List<MediaSource> mediaSourceList = new ArrayList<>();

    /*可见好友集合*/
    private List<Long> viewFriendList= new ArrayList<>();

    private String mediaUrl = "";

    private String resourceName;

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setMediaStatus(String mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMediaStatus() {
        return mediaStatus;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public List<MediaSource> getMediaSource() {
        return mediaSourceList;
    }

    public void setMediaSource(List<MediaSource> mediaSource) {
        this.mediaSourceList = mediaSource;
    }

    public List<Long> getViewFriendList() {
        return viewFriendList;
    }

    public void setViewFriendList(List<Long> viewFriendList) {
        this.viewFriendList = viewFriendList;
    }

    public static class MediaSource {
        /**
         * url : https://liuxin-1255602279.cos.ap-guangzhou.myqcloud.com/goods/2019/11/%E5%A5%B6.png
         * mediaImageTag : [{"tagX":"110","tagY":"120","tagComment":"这是一个标签"},{"tagX":"180","tagY":"190","tagComment":"这是两个标签"}]
         */

        private String url;
        private List<MediaImageTag> mediaImageTag = new ArrayList<>();

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<MediaImageTag> getMediaImageTag() {
            return mediaImageTag;
        }

        public void setMediaImageTag(List<MediaImageTag> mediaImageTag) {
            this.mediaImageTag = mediaImageTag;
        }

        public static class MediaImageTag {
            /**
             * tagX : 110
             * tagY : 120
             * tagComment : 这是一个标签
             */

            private String tagX="0";
            private String tagY="0";
            private String tagComment="";

            public String getTagX() {
                return tagX;
            }

            public void setTagX(String tagX) {
                this.tagX = tagX;
            }

            public String getTagY() {
                return tagY;
            }

            public void setTagY(String tagY) {
                this.tagY = tagY;
            }

            public String getTagComment() {
                return tagComment;
            }

            public void setTagComment(String tagComment) {
                this.tagComment = tagComment;
            }
        }
    }
}
