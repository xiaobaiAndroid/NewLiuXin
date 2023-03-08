package module.common.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * @describe: 商品实体
 * @date: 2020/3/17
 * @author: Mr Bai
 */
public class Goods implements Parcelable {



    /**
     * goodsId : 405249070450749440
     * goodsSkuId : null
     * actId : null
     * cateId : 393996664597524480
     * brandId : 367068316495654912
     * cateName : null
     * goodsUrl : https://liuxin-1301077617.cos.ap-guangzhou.myqcloud.com/goods/2020/4/108004839112986082%20%281%29.jpg
     * goodsTitle : 内衣女小胸聚拢无钢圈收副乳调整型防下垂厚少女性感
     * salePrice : 4700
     * keyword : 小胸聚拢收副乳调整型文胸
     * goodsDetail : null
     * income1 : 400
     * income2 : 200
     * income3 : 0
     * sales : 0
     * adminId : null
     * storeId : 351886575548116992
     * storeName : null
     * freightRemark : null
     * goodsImages : null
     * descImages : null
     * goodsSkuList : null
     * colorImages : null
     * act : null
     * attrList : null
     */

    private String id;
    private String goodsId;
    private String goodsSkuId;
    private String actId;
    private String cateId;
    private String brandId;
    private String cateName;
    private String goodsUrl;
    private String goodsTitle;
    private int salePrice;
    private String keyword;
    private Object goodsDetail;
    private int income1;
    private int income2;
    private int income3;
    private int sales;
    private String adminId;
    private String storeId;
    private String storeName;
    private Object freightRemark;
    private List<GoodsDetailImage> goodsImages;
    private List<GoodsDetailImage> descImages;
    private List<GoodsDetailSku> goodsSkuList;
    private List<GoodsColorImage> colorImages;
    private Object act;
    private List<GoodsSkuAttribute> attrList;

    private String goodsName;

    /*商家*/
    private Merchant merchant;

    public Goods() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
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

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Object getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(Object goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public int getIncome1() {
        return income1;
    }

    public void setIncome1(int income1) {
        this.income1 = income1;
    }

    public int getIncome2() {
        return income2;
    }

    public void setIncome2(int income2) {
        this.income2 = income2;
    }

    public int getIncome3() {
        return income3;
    }

    public void setIncome3(int income3) {
        this.income3 = income3;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
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

    public Object getFreightRemark() {
        return freightRemark;
    }

    public void setFreightRemark(Object freightRemark) {
        this.freightRemark = freightRemark;
    }

    public List<GoodsDetailImage> getGoodsImages() {
        return goodsImages;
    }

    public void setGoodsImages(List<GoodsDetailImage> goodsImages) {
        this.goodsImages = goodsImages;
    }

    public List<GoodsDetailImage> getDescImages() {
        return descImages;
    }

    public void setDescImages(List<GoodsDetailImage> descImages) {
        this.descImages = descImages;
    }

    public List<GoodsDetailSku> getGoodsSkuList() {
        return goodsSkuList;
    }

    public void setGoodsSkuList(List<GoodsDetailSku> goodsSkuList) {
        this.goodsSkuList = goodsSkuList;
    }

    public List<GoodsColorImage> getColorImages() {
        return colorImages;
    }

    public void setColorImages(List<GoodsColorImage> colorImages) {
        this.colorImages = colorImages;
    }

    public Object getAct() {
        return act;
    }

    public void setAct(Object act) {
        this.act = act;
    }

    public List<GoodsSkuAttribute> getAttrList() {
        return attrList;
    }

    public void setAttrList(List<GoodsSkuAttribute> attrList) {
        this.attrList = attrList;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
