package module.goods.detail;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import module.common.data.entity.Goods;
import module.common.data.entity.Shop;

public interface GoodsDetailContract {

    interface View {

        void getGoodsDetailResult(List<DetailMultiEntity> goods);

        void getShopInfoResult(Shop shop);

        void addShoppingCartSuccess(String message);

        void addShoppingCartFail(String message);

        void collectGoodsSuccess(String message);

        void collectGoodsFail(String message);

        void getGoodsDetailFail(String message);
    }

    interface Presenter{

        /**
         * @describe: 获取商品详情
         * @date: 2020/5/2
         */
        void getGoodsDetail(@Nullable String goodsId, @Nullable String actId);

        /**
         * @describe: 获取店铺信息
         * @date: 2020/5/3
         */
        void getShopInfo(@Nullable String storeId);

        /**
         * @describe:
         * @date: 2020/5/5
         * @param selectedSkuId 选中的商品规格id
         */
        void addShoppingCart(@Nullable Goods goods, @Nullable String selectedSkuId, int buyNumber);

        /**
         * @describe: 收藏商品
         * @date: 2020/5/5
         */
        void collectGoods(@Nullable String goodsId);
    }
}
