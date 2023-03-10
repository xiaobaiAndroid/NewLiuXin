package module.music.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import module.common.base.BaseActivity
import module.common.data.entity.MusicCategory
import module.common.event.MessageEvent
import module.common.utils.ImmersionBarUtils
import module.common.utils.StatusBarUtils
import module.music.MusicAdapter
import module.music.R
import module.music.databinding.MusicActivityMusicCategoryDetailBinding
import org.greenrobot.eventbus.EventBus

class MusicCategoryDetailActivity :
    BaseActivity<MusicActivityMusicCategoryDetailBinding, MusicCategoryDetailViewModel>() {

    private val musicAdapter = CategoryDetailAdapter()

    override fun createViewModel(): MusicCategoryDetailViewModel {
        return viewModels<MusicCategoryDetailViewModel>().value
    }

    override fun getBindingView(): MusicActivityMusicCategoryDetailBinding {
        return MusicActivityMusicCategoryDetailBinding.inflate(layoutInflater)
    }

    override fun initStatusBar() {
        ImmersionBarUtils.defaultBuild(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        StatusBarUtils.setMarginStatusBarHeight(this,binding.titleTV)
        StatusBarUtils.setMarginStatusBarHeight(this,binding.cancelTV)

        binding.cancelTV.text = resources.getString(R.string.music_back)
        binding.cancelTV.setOnClickListener {
            onBackPressed()
        }

        binding.contentRV.layoutManager = LinearLayoutManager(this)
        binding.contentRV.adapter = musicAdapter

        musicAdapter.setOnItemClickListener { adapter, view, position ->
            val music = musicAdapter.getItem(position)
            val messageEvent = MessageEvent(MessageEvent.Type.MUSIC_SELECT_MUSIC)
            messageEvent.obj = music
            EventBus.getDefault().post(messageEvent)
            onBackPressed()
        }

        musicAdapter.loadMoreModule.setOnLoadMoreListener {
            viewModel.getData(applicationContext)
        }

    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel.musicsLiveData.observe(this){
            if(viewModel.req.pageNumber == 1){
                musicAdapter.setList(it)
            }else{
                musicAdapter.addData(it)
                if(it.isEmpty()){
                    musicAdapter.loadMoreModule.loadMoreEnd()
                }else{
                    musicAdapter.loadMoreModule.loadMoreComplete()
                }

            }

        }

       intent.extras?.getParcelable<MusicCategory>("musicCategory")?.let {
            binding.titleTV.text = it.musicTypeName ?: ""
            it.musicType?.let { musicType->
                viewModel.setMusicType(musicType)
            }
            viewModel.getData(applicationContext)
        }
    }
}