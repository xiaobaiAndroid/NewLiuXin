package module.common.data.response;

import module.common.data.api.BaseResp;

public class AttentionResp extends BaseResp<AttentionResp.AttentionStatus> {

    public static class AttentionStatus{

        private String state;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
