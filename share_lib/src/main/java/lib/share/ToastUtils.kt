package lib.share

import android.content.Context
import android.text.TextUtils
import android.widget.Toast

class ToastUtils {

    companion object {
        fun setMessage(mContext: Context, text: String?) {
            if (!TextUtils.isEmpty(text)) {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show()
            }
        }

        fun showMessageLong(context: Context, text: String?) {
            if (!TextUtils.isEmpty(text)) {
                Toast.makeText(context, text, Toast.LENGTH_LONG).show()
            }
        }

    }
}