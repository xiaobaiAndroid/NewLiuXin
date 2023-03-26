package module.address.city;

import android.animation.Animator;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import module.address.R;

/**
 * 热门城市
 * @date: 2017/12/27
 */

public class HostCityAdapter extends BaseQuickAdapter<CitySuspension,BaseViewHolder> {


    public HostCityAdapter(@Nullable List<CitySuspension> data) {
        super(R.layout.map_item_host_city,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CitySuspension city) {
        if(city!=null){
            if(city.getCity()!=null){

                helper.setText(R.id.cityTV,city.getCity());
            }else{
                helper.setText(R.id.cityTV,"");
            }
        }
    }

    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if(index<getData().size()){
            anim.setStartDelay(index*150);
        }
    }
}
