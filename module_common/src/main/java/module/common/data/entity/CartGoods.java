package module.common.data.entity;



import java.util.List;

import module.common.status.SelectedStatus;


/**
 * @describe: 购物车的商品
 * @date: 2020/5/20
 * @author: Mr Bai
 */
public class CartGoods {

    /**
     * cartId : 407823440096407552
     * goodsId : 407755672550518784
     * goodsSkuId : 407755673649426434
     * actId : 0
     * buyCount : 2
     * goodsUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/5/1432345304353264020200505_170617_018.jpg
     * goodsTitle : 020实拍孕妇长款绣花孕妇纯棉过膝连衣裙
     * salePrice : 4900
     * income1 : 200
     * skuAttrItemList : [{"skuAttrId":"1","skuAttrItemId":"1","skuAttrItemName":"零售"},{"skuAttrId":"2","skuAttrItemId":"216","skuAttrItemName":"银灰"},{"skuAttrId":"3","skuAttrItemId":"10","skuAttrItemName":"XL码"}]
     * preCount : 1
     * stock : 999
     * storeId : 407735816245620736
     * storeName : 褐马
     * state : 1
     */

    private String cartId;
    private String goodsId;
    private String goodsSkuId;
    private String actId;
    private int buyCount;
    private String goodsUrl;
    private String goodsTitle;
    private String salePrice;
    private String income1;
    private int preCount;
    private int stock;
    private String storeId;
    private String storeName;

    /*选中状态 1选中，0未选中*/
    private int state = SelectedStatus.NONE.ordinal();
    private List<SkuAttrItem> skuAttrItemList;

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getIncome1() {
        return income1;
    }

    public void setIncome1(String income1) {
        this.income1 = income1;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(String goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }


    public int getPreCount() {
        return preCount;
    }

    public void setPreCount(int preCount) {
        this.preCount = preCount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<SkuAttrItem> getSkuAttrItemList() {
        return skuAttrItemList;
    }

    public void setSkuAttrItemList(List<SkuAttrItem> skuAttrItemList) {
        this.skuAttrItemList = skuAttrItemList;
    }

    public static class SkuAttrItem {
        /**
         * skuAttrId : 1
         * skuAttrItemId : 1
         * skuAttrItemName : 零售
         */

        private String skuAttrId;
        private String skuAttrItemId;
        private String skuAttrItemName;

        public String getSkuAttrId() {
            return skuAttrId;
        }

        public void setSkuAttrId(String skuAttrId) {
            this.skuAttrId = skuAttrId;
        }

        public String getSkuAttrItemId() {
            return skuAttrItemId;
        }

        public void setSkuAttrItemId(String skuAttrItemId) {
            this.skuAttrItemId = skuAttrItemId;
        }

        public String getSkuAttrItemName() {
            return skuAttrItemName;
        }

        public void setSkuAttrItemName(String skuAttrItemName) {
            this.skuAttrItemName = skuAttrItemName;
        }
    }
}
