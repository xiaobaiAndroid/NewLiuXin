package com.bzf.module_imageeditor.label

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bzf.module_imageeditor.R
import com.bzf.module_imageeditor.databinding.ImgFragmentLabelSelectBinding
import com.bzf.module_imageeditor.view.LinearSpaceDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import module.common.base.BaseFragment
import module.common.event.MessageEvent
import module.common.utils.ToastUtils
import module.map.LocationHelper
import module.map.MapPOIProvider
import module.map.entity.GPSUtils


/**
 *@author: baizf
 *@date: 2023/3/14
 */
class LabelAddressSelectFragment :
    BaseFragment<ImgFragmentLabelSelectBinding, LabelAddressSelectViewModel>() {

    private val mAdapter = LableAddressAdapter()

    private var permissionLauncher =
        activity?.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map->

            val set = map.entries
            var granted = true
            set.forEach {
                granted = it.value
            }

            startLocation()
//            if (map.get(Manifest.permission.ACCESS_COARSE_LOCATION) == true) {
//                startLocation()
//            } else {
                ToastUtils.setMessage(
                    requireContext(),
                    resources.getString(R.string.img_no_location_permisison)
                )

//            }
        }


    private var openGPSLauncher =  activity?.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        checkPermission()
    }


    private fun startLocation() {
        val mapPOIProvider = MapPOIProvider()
        lifecycleScope.launch(Dispatchers.IO) {
            val poiList = mapPOIProvider.getPOIList(requireActivity(),false)
            withContext(Dispatchers.Main){
                mAdapter.setList(poiList)
            }
        }

    }

    override fun createViewModel(): LabelAddressSelectViewModel {
        return viewModels<LabelAddressSelectViewModel>().value
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ImgFragmentLabelSelectBinding {
        isAlone = true
        return ImgFragmentLabelSelectBinding.inflate(inflater, container, false)
    }


    override fun initData() {
        LocationHelper.agreePrivacy()
        activity?.let {
            if (!GPSUtils.checkGPSIsOpen(it)) {
                openGPSLauncher?.launch(GPSUtils.getOpenGPSSettingsIntent())
            } else {
                checkPermission()
            }
        }



    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED) {

                startLocation()
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ToastUtils.setMessage(
                    requireActivity(),
                    resources.getString(R.string.img_user_disable_location_permisison)
                )
            } else {
                permissionLauncher?.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                )
            }

        }
    }


    override fun disposeMessageEvent(event: MessageEvent?) {
        super.disposeMessageEvent(event)
        event?.let {
            if (MessageEvent.Type.UPDATE_USERINFO === it.type) {
                viewModel.getData()
            }
        }
    }


    override fun initView() {
        binding.contentRV.layoutManager = LinearLayoutManager(requireContext())
        binding.contentRV.adapter = mAdapter

        val padding = resources.getDimension(R.dimen.dp_16).toInt()
        val spaceDecoration = LinearSpaceDecoration(
            mAdapter,
            padding,
            padding, padding
        )

        spaceDecoration.setDrawHeader(true)

        binding.contentRV.addItemDecoration(spaceDecoration)
    }

    override fun onDestroy() {
        permissionLauncher?.unregister()
        openGPSLauncher?.unregister()
        super.onDestroy()
    }

}