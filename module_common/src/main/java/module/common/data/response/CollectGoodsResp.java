package module.common.data.response;

import module.common.base.CommonListResp;
import module.common.data.api.BaseResp;
import module.common.data.entity.Goods;

public class CollectGoodsResp extends BaseResp<CommonListResp<CollectGoodsResp.Data>> {


    public static class Data {

        /**
         * id : 395047535410294784
         * userId : 384489609499127808
         * goodsId : 393967858688077824
         * state : 1
         * createBy : 18664868453
         * createTime : 2020-03-31 15:46:02
         * updateBy : 18664868453
         * updateTime : 2020-03-31 15:46:02
         * goods : {"id":"393967858688077824","cateId":"354431966315425792","brandId":"367068316495654912","keyword":"法国进口锁边布细致丝滑","goodsTag":1,"goodsUrl":"https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/3/5552011937921736WechatIMG7.jpeg","goodsName":"Love is the answer","goodsTitle":"mdl爱丽儿","salePrice":2880,"income1":200,"income2":200,"income3":200,"sales":0,"storeId":"387780930716446720","storeType":1,"state":1,"createBy":"auto","createTime":"2020-03-28 16:15:47","updateBy":"auto","updateTime":"2020-03-29 17:52:52","goodsImages":null,"goodsSkuList":null,"colorImages":null,"goodsSkuAttrs":null}
         */

        private String id;
        private String userId;
        private String goodsId;
        private int state;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private Goods goods;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Goods getGoods() {
            return goods;
        }

        public void setGoods(Goods goods) {
            this.goods = goods;
        }

    }

}
