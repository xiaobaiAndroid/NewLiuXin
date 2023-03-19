package module.common.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import module.common.type.LanguageType
import java.util.*

/*
* @describe: 设置语言环境工具类
* @author: bzf
* @date: 2020/8/12
*/
class LanguageConfigUtils {

    companion object {

        fun setLanguageConfig(activity: Activity) {

            var locale: Locale

            val languageType = getCurrentSetLanguage(activity)
            if (languageType == LanguageType.CN.value) {
                locale = Locale.CHINA
            } else {
                locale = Locale.US
            }
            setAppLanguage(activity, locale)
        }


        /**
         * 更新应用语言（核心）
         * @param context
         * @param locale
         */
        fun setAppLanguage(context: Context, locale: Locale) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            val configuration = resources.configuration
            //Android 7.0以上的方法
            if (Build.VERSION.SDK_INT >= 24) {
                configuration.setLocale(locale)
                configuration.setLocales(LocaleList(locale))
                context.createConfigurationContext(configuration)
                //实测，updateConfiguration这个方法虽然很多博主说是版本不适用
                //但是我的生产环境androidX+Android Q环境下，必须加上这一句，才可以通过重启App来切换语言
                resources.updateConfiguration(configuration, metrics)
            } else
            //Android 4.1 以上方法
                configuration.setLocale(locale)
            resources.updateConfiguration(configuration, metrics)
        }

        /*
        * @describe: 获取当前的语言环境
        * @date: 11/25/20
        */
        fun getCurrentSetLanguage(context: Context): Int {
            var defaultLanguage: Int
            if (isZh(context)) {
                defaultLanguage = LanguageType.CN.value
            } else {
                defaultLanguage = LanguageType.EN.value
            }
            return SPHelper.get(context, SPHelper.LANGUAGE, defaultLanguage) as Int
        }


        /*
        * @describe: 保存设置的语言环境
        * @date: 11/25/20
        */
        fun saveCurrentLanguage(context: Context, language: Int) {
            SPHelper.put(context, SPHelper.LANGUAGE, language)
        }

        /*
        * @describe: 是否是中文环境
        * @date: 11/26/20
        */
        fun isZh(context: Context): Boolean {
            val locale = getAppLocale(context)
            val language = locale.language
            LogUtils.printI("language=" + language)
            return language.endsWith("zh")
        }


        /**
         * 获取应用语言
         */
        fun getAppLocale(context: Context): Locale {
            var local: Locale
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                local = context.resources.configuration.locales.get(0)
            } else {
                local = context.resources.configuration.locale;
            }
            return local
        }

        /**
         * 获取系统语言
         */
        fun getSystemLanguage(): LocaleListCompat {
            val configuration = Resources.getSystem().configuration
            val locales = ConfigurationCompat.getLocales(configuration)
            return locales
        }

    }
}