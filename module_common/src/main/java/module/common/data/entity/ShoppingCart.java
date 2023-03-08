package module.common.data.entity;

import java.util.List;

/**
 * @describe: 购物车
 * @date: 2020/5/20
 * @author: Mr Bai
 */
public class ShoppingCart {

    /**
     * storeId : 407735816245620736
     * storeName : 褐马
     * cartList : [{"cartId":"407823440096407552","goodsId":"407755672550518784","goodsSkuId":"407755673649426434","actId":"0","buyCount":2,"goodsUrl":"https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/5/1432345304353264020200505_170617_018.jpg","goodsTitle":"020实拍孕妇长款绣花孕妇纯棉过膝连衣裙","salePrice":4900,"income1":200,"skuAttrItemList":[{"skuAttrId":"1","skuAttrItemId":"1","skuAttrItemName":"零售"},{"skuAttrId":"2","skuAttrItemId":"216","skuAttrItemName":"银灰"},{"skuAttrId":"3","skuAttrItemId":"10","skuAttrItemName":"XL码"}],"preCount":1,"stock":999,"storeId":"407735816245620736","storeName":"褐马","state":1}]
     * expressType : null
     * remark : null
     */

    private String storeId;
    private String storeName;
    private Object expressType;
    private String remark;
    private List<CartGoods> cartList;

    /*选中状态：编辑操作*/
    private boolean selected = false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public Object getExpressType() {
        return expressType;
    }

    public void setExpressType(Object expressType) {
        this.expressType = expressType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CartGoods> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartGoods> cartList) {
        this.cartList = cartList;
    }

}
