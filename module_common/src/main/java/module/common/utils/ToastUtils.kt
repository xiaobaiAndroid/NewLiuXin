package module.common.utils

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

object ToastUtils {
    fun setMessage(mContext: Context?, text: String?) {
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
        }
    }

    fun showMessageLong(mContext: Context?, text: String?) {
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show()
        }
    }

}