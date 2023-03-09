package module.live.publish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import module.common.base.BaseActivity
import module.common.data.entity.CliqueCategory
import module.common.data.entity.Music
import module.common.data.request.PublishReq
import module.common.type.MediaType
import module.common.utils.ARouterHelper
import module.common.utils.StatusBarUtils
import module.common.view.GridSpaceDecoration
import module.live.R
import module.live.databinding.LiveActivityPublishBinding
import module.live.publish.category.CategoriseView

/*
* @describe: 动态发布页
* @author: bzf
* @date: 2023/3/9
*/
class PublishActivity : BaseActivity<LiveActivityPublishBinding, PublishViewModel>() {

    lateinit var mediaAdpter:MediaAdapter

    var paths: ArrayList<String>? = null
    var mediaType: String? = null

    val publishReq = PublishReq()

    /*上传的路径*/
    val uploadUrls = HashMap<String,String>()

    var popupView: BasePopupView? = null

    var categoryList:MutableList<CliqueCategory>? = null

    var music: Music? = null

    override fun createViewModel(): PublishViewModel {
        return viewModels<PublishViewModel>().value
    }

    override fun getBindingView(): LiveActivityPublishBinding {
        return LiveActivityPublishBinding.inflate(layoutInflater)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this, binding.cancelTV)
        StatusBarUtils.setMarginStatusBarHeight(this, binding.publishTV)

        binding.cancelTV.setOnClickListener {
            onBackPressed()
        }

        binding.publishTV.setOnClickListener {

        }

        binding.selectCategoryTV.setOnClickListener {
            if(categoryList!=null){
                popupView  = XPopup.Builder(this)
                    .hasShadowBg(false)
                    .asCustom(CategoriseView(this, categoryList!!))
                    .show()
            }
        }

        mediaAdpter = MediaAdapter(this,ArrayList<MediaMultiEntity>())

        val spanCount = 3
        binding.mediaRV.layoutManager = GridLayoutManager(this, spanCount)
        binding.mediaRV.adapter = mediaAdpter
        val span = resources.getDimension(module.common.R.dimen.dp_12).toInt()
        val spaceDecoration = GridSpaceDecoration(mediaAdpter, span, spanCount)
        spaceDecoration.setMargin(span/2)
        binding.mediaRV.addItemDecoration(spaceDecoration)


        mediaAdpter.setOnItemClickListener { adapter, view, position ->
            val multiEntity = mediaAdpter.getItem(position)


        }

        binding.addLocationTV.setOnClickListener {
            ARouterHelper.openPath(this, ARouterHelper.LOCATIONS)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        
    }

}