package module.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils {

	@SuppressWarnings("unused")
	private Context mContext;

	public static void setMessage(Context mContext, String text) {
		if(!TextUtils.isEmpty(text)){
			Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();

		}
	}

	public static void showMessageLong(Context mContext, String text) {
		if(!TextUtils.isEmpty(text)){
			Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();

		}
	}

	public static Toast getIntance(Context mContext) {
		return new Toast(mContext);
	}

	public static void showToastInCenter() {

	}
}
