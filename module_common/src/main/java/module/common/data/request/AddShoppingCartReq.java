package module.common.data.request;

/**
 * @describe: 添加购物车请求
 * @date: 2020/5/5
 * @author: Mr Bai
 */
public class AddShoppingCartReq {


    /**
     * goodsId : 26
     * actId : 0
     * goodsSkuId : 325828120630669312
     * buyCount : 1
     */

    private String goodsId;
    /*活动id  从活动页面进入商品详情的时候带入*/
    private String actId;
    /*	商品规格id*/
    private String goodsSkuId;

    /*商品数量*/
    private int buyCount;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getGoodsSkuId() {
        return goodsSkuId;
    }

    public void setGoodsSkuId(String goodsSkuId) {
        this.goodsSkuId = goodsSkuId;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}
