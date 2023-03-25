package module.common.data.request;

import module.common.base.BaseListReq;

/**
 * @describe: 店铺商品请求
 * @date: 2020/6/16
 * @author: Mr Bai
 */
public class ShopGoodsReq extends BaseListReq<ShopGoodsReq.QueryObj> {

    public ShopGoodsReq(ShopGoodsReq.QueryObj queryObj) {
        super(queryObj);
    }

    public static class QueryObj{
        private String storeId;

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }
    }
}
