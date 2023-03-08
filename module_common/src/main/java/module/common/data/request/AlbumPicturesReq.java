package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 图库图片列表
 * @date: 2020/6/28
 * @author: Mr Bai
 */
public class AlbumPicturesReq extends BaseListReq<AlbumPicturesReq.QueryObj> {

    public static class QueryObj{
        private String fileFolderId;

        public String getFileFolderId() {
            return fileFolderId;
        }

        public void setFileFolderId(String fileFolderId) {
            this.fileFolderId = fileFolderId;
        }
    }

}
