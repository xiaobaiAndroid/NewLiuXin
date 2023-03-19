package module.common.utils

import android.content.Context
import android.os.IBinder
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Created by ivan on 2017/12/8.
 */
/**
 * 打开或关闭软键盘
 *
 * @author zhy
 */
object KeyBoardUtils {
    /**
     * 打卡软键盘
     *
     * @param mEditText
     * 输入框
     * @param mContext
     * 上下文
     */
    fun openKeybord(mEditText: EditText?, mContext: Context) {
        val imm = mContext
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText
     * 输入框
     * @param mContext
     * 上下文
     */
    fun closeKeybord(mEditText: EditText, mContext: Context) {
        val imm = mContext
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText
     * 输入框
     * @param mContext
     * 上下文
     */
    fun closeKeybordByToken(windowToken: IBinder?, mContext: Context) {
        val imm = mContext
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}