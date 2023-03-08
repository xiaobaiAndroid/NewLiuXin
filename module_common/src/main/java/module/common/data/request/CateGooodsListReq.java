package module.common.data.request;

import module.common.base.BaseListReq;

import java.util.List;

/**
 * @describe: 根据类别获取商品列表
 * @date: 2020/4/27
 * @author: Mr Bai
 */
public class CateGooodsListReq extends BaseListReq<CateGooodsListReq.QueryObj> {

    public static class QueryObj{

        /*分类id,首页商品列表不传*/
        private String cateId;

        /*分类id集合*/
        private List<String> cateIds;

        private int state = 1;

        /*商品标题*/
        private String goodsTitle;

        /*品牌id*/
        private List<String> brandIds;

        /*筛选后的skuid*/
        private List<String> skuIds;

        /*价格从大到小*/
        private String priceDesc;

        /*价格从小到大 Asc*/
        private String priceAsc;

        /*销量排序：Desc或Asc*/
        private String salesDesc;

        /*价格区间*/
        private String minPrice;
        private String maxPrice;

        public String getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(String minPrice) {
            this.minPrice = minPrice;
        }

        public String getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(String maxPrice) {
            this.maxPrice = maxPrice;
        }

        public String getGoodsTitle() {
            return goodsTitle;
        }

        public void setGoodsTitle(String goodsTitle) {
            this.goodsTitle = goodsTitle;
        }

        public String getCateId() {
            return cateId;
        }

        public void setCateId(String cateId) {
            this.cateId = cateId;
        }

        public List<String> getBrandIds() {
            return brandIds;
        }

        public void setBrandIds(List<String> brandIds) {
            this.brandIds = brandIds;
        }

        public List<String> getSkuIds() {
            return skuIds;
        }

        public void setSkuIds(List<String> skuIds) {
            this.skuIds = skuIds;
        }

        public String getPriceDesc() {
            return priceDesc;
        }

        public void setPriceDesc(String priceDesc) {
            this.priceDesc = priceDesc;
        }

        public String getPriceAsc() {
            return priceAsc;
        }

        public void setPriceAsc(String priceAsc) {
            this.priceAsc = priceAsc;
        }

        public String getSalesDesc() {
            return salesDesc;
        }

        public void setSalesDesc(String salesDesc) {
            this.salesDesc = salesDesc;
        }

        public List<String> getCateIds() {
            return cateIds;
        }

        public void setCateIds(List<String> cateIds) {
            this.cateIds = cateIds;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
