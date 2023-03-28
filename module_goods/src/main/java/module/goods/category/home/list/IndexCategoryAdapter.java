package module.goods.category.home.list;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import module.common.data.entity.GoodsCategory;
import module.common.utils.ImageLoadHelper;
import module.goods.R;

public class IndexCategoryAdapter extends BaseQuickAdapter<GoodsCategory, BaseViewHolder> {

    public IndexCategoryAdapter(@Nullable List<GoodsCategory> data) {
        super(R.layout.goods_item_chhild_category,data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsCategory item) {
        if(item!=null){
            TextView contentTV = helper.getView(R.id.contentTV);
            contentTV.setText(item.getCateName());
            if(item.getId().equals("0")){
                helper.setImageResource(R.id.iv,item.getResourceId());
            }else{
                ImageView iv = helper.getView(R.id.iv);
                ImageLoadHelper.INSTANCE.loadCircle(iv,item.getCateUrl(), module.common.R.drawable.ic_default_photo);
            }

        }
    }
}
