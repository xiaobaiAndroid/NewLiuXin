package module.music

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import module.common.base.BaseActivity
import module.common.event.MessageEvent
import module.common.utils.ImmersionBarUtils
import module.common.utils.StatusBarUtils
import module.music.category.MusicCategoryDetailActivity
import module.music.databinding.MusicActivityMusicHomeBinding
import org.greenrobot.eventbus.EventBus

/*
* @describe:
* @author: bzf
* @date: 2023/3/9
*/
class MusicHomeActivity : BaseActivity<MusicActivityMusicHomeBinding, MusicHomeViewModel>() {

    val musicAdapter = MusicAdapter(mutableListOf())



    override fun createViewModel(): MusicHomeViewModel {
        return viewModels<MusicHomeViewModel>().value
    }

    override fun getBindingView(): MusicActivityMusicHomeBinding {
        return MusicActivityMusicHomeBinding.inflate(layoutInflater)
    }

    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        if(event?.type === MessageEvent.Type.MUSIC_SELECT_MUSIC){
            onBackPressed()
        }
    }


    override fun initStatusBar() {
        ImmersionBarUtils.init(this)
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
            val musicMultiEntity = musicAdapter.getItem(position)
            val messageEvent = MessageEvent(MessageEvent.Type.MUSIC_SELECT_MUSIC)
            messageEvent.obj = musicMultiEntity.music
            EventBus.getDefault().post(messageEvent)
            onBackPressed()
        }

        musicAdapter.addChildClickViewIds(R.id.lookMoreTV)
        musicAdapter.setOnItemChildClickListener { adapter, view, position ->
            val musicMultiEntity = musicAdapter.getItem(position)

            val intent = Intent(this, MusicCategoryDetailActivity::class.java)
            intent.putExtra("musicCategory",      musicMultiEntity.category)
            startActivity(intent)
        }

    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel.musicEntitesLiveData.observe(this){
            musicAdapter.setList(it)
        }

        viewModel.getData(applicationContext)
    }

}