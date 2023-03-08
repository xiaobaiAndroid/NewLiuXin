package module.common.data.request;

import module.common.base.BaseListReq;

public class MusicsReq extends BaseListReq<MusicsReq.QueryObj> {

    public static class QueryObj{

        /*音乐分类*/
        private int musicType;

        public int getMusicType() {
            return musicType;
        }

        public void setMusicType(int musicType) {
            this.musicType = musicType;
        }
    }
}
