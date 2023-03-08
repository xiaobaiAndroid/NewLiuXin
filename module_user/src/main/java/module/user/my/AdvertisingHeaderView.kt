package module.user.my

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import module.user.R

class AdvertisingHeaderView(context: Context): FrameLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.user_header_advertising,this)
    }
}