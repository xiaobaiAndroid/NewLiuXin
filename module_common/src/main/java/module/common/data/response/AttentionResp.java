package module.common.data.response;

import module.common.data.api.BaseResp;

public class AttentionResp extends BaseResp<AttentionResp.AttentionStatus> {


    //	"data": {
    //		"id": "486445290514427904",
    //		"userId": "359603221909876736",
    //		"likeUserId": "359553114711666688",
    //		"state": 1,
    //		"createBy": "13539713955",
    //		"createTime": "2020-12-08 20:48:05",
    //		"updateBy": "13539713955",
    //		"updateTime": "2023-03-20 22:19:54",
    //		"extend": null
    //	}
    public static class AttentionStatus{

        private int state;

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
