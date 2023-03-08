package module.user.my

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import module.common.base.BaseFragment
import module.common.picture.GlideEngine
import module.common.utils.ARouterHelper
import module.common.utils.DensityUtil
import module.common.view.LinearDividerDecoration
import module.user.R
import module.user.databinding.UserFragmentMyBinding

/*
* @describe: 我的页面
* @author: bzf
* @date: 2023/3/8
*/
@Route(path = ARouterHelper.FRA_MY)
class MyFragment : BaseFragment<UserFragmentMyBinding, MyViewModel>() {

    val myItemAdapter = MyItemAdapter(mutableListOf())

    override fun createViewModel(): MyViewModel {
        return viewModels<MyViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): UserFragmentMyBinding {
        return UserFragmentMyBinding.inflate(inflater, container, false)
    }

    override fun initData() {
        viewModel.userInfoLiveData.observe(viewLifecycleOwner
        ) {
            binding.userHeaderV.setData(it)
        }
        viewModel.mUploadProgressLiveData.observe(viewLifecycleOwner){

        }
        viewModel.mUserBackgroundUrlLiveData.observe(viewLifecycleOwner){
            binding.userHeaderV.updateBackground(it)
        }

        viewModel.getNewUserInfo(requireContext())
    }

    override fun initView() {
        binding.contentRV.layoutManager = LinearLayoutManager(context)
        binding.contentRV.adapter = myItemAdapter

        myItemAdapter.addHeaderView(AdvertisingHeaderView(requireContext()))

        val items = mutableListOf<MyItemEntity>()
        items.add(MyItemEntity(resources.getString(R.string.user_ic_pay), R.drawable.user_ic_order))
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_my_order),
                R.drawable.user_ic_album
            )
        )
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_my_clique),
                R.drawable.user_ic_pay
            )
        )
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_apply_store),
                R.drawable.user_ic_album
            )
        )
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_my_album),
                R.drawable.user_ic_clique
            )
        )
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_my_collect),
                R.drawable.user_ic_collect
            )
        )
        items.add(
            MyItemEntity(
                resources.getString(R.string.user_setting),
                R.drawable.user_ic_setting
            )
        )

        myItemAdapter.setList(items)

        val linearDividerDecoration = LinearDividerDecoration(
            myItemAdapter,
            ColorDrawable(resources.getColor(module.common.R.color.cl_f1f1f1)),
            DensityUtil.dip2px(requireContext(), 1f)
        )
        linearDividerDecoration.setmPaddingLeft(DensityUtil.dip2px(requireContext(), 10f))
        linearDividerDecoration.setDrawHeader(true)
        linearDividerDecoration.setDrawFoot(true)
        binding.contentRV.addItemDecoration(linearDividerDecoration)

        myItemAdapter.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    val bundle = Bundle()
                    bundle.putParcelable(ARouterHelper.Key.MONEY, viewModel.money)
                    ARouterHelper.openPath(activity, ARouterHelper.RECHARGE, bundle)
                }
                1 -> {
                    ARouterHelper.openPath(activity, ARouterHelper.ORDER_LIST)
                }
                2 -> {
                    ARouterHelper.openPath(activity, ARouterHelper.MY_CLIQUE)
                }
                3 -> {
                    if (viewModel.merchant != null) { //已有店铺
                        val bundle = Bundle()
                        bundle.putParcelable(ARouterHelper.Key.ENTITY, viewModel.merchant)
                        bundle.putParcelable(ARouterHelper.Key.STORE, viewModel.store)
                        ARouterHelper.openPath(activity, ARouterHelper.STORE_INFO, bundle)
                    } else {    //申请店铺
                        ARouterHelper.openPath(activity, ARouterHelper.APPLY_STORE)
                    }
                }
                4 -> {
                    val bundle = Bundle()
                    bundle.putString(ARouterHelper.Key.USERID, viewModel.userInfoLiveData.value?.userId)
                    ARouterHelper.openPath(activity, ARouterHelper.ALBUM_HOME, bundle)
                }
                5 -> {
                    ARouterHelper.openPath(activity, ARouterHelper.MY_COLLECT)
                }
                6 -> {
                    ARouterHelper.openPath(activity, ARouterHelper.SETTING)
                }

            }
        }

        binding.userHeaderV.setOnClickListener {
            //更换背景
            PictureSelector.create(activity)
                .openGallery(SelectMimeType.ofImage())
                .setMaxSelectNum(1)
                .setImageEngine(GlideEngine.createGlideEngine())
                .forResult(object: OnResultCallbackListener<LocalMedia>{
                    override fun onResult(result: ArrayList<LocalMedia>?) {
                        if (!result.isNullOrEmpty()) {
                            val localMedia = result[0]
                            val path = localMedia.path
                            uploadUserPhoto(path)
                        }
                    }

                    override fun onCancel() {
                    }
                })
        }
    }

    private fun uploadUserPhoto(path: String?) {
        path?.let {
            viewModel.uploadUserPhoto(requireContext(),it)
        }

    }

}