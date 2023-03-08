package module.common.data.request;

import java.util.List;

/**
 * @describe: 删除购物车商品的请求
 * @date: 2020/5/20
 * @author: Mr Bai
 */
public class DeleteCartGoodsReq {

    private List<String> cartIds;

    /*是否同时加入收藏*/
    private boolean move2Fav;

    public List<String> getCartIds() {
        return cartIds;
    }

    public void setCartIds(List<String> cartIds) {
        this.cartIds = cartIds;
    }

    public boolean isMove2Fav() {
        return move2Fav;
    }

    public void setMove2Fav(boolean move2Fav) {
        this.move2Fav = move2Fav;
    }
}
