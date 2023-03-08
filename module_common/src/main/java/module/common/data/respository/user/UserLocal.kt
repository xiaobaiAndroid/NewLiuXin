/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package module.common.data.respository.user

import android.content.Context
import android.text.TextUtils
import module.common.data.db.AppDatabase
import module.common.data.db.entity.UserInfoTable
import module.common.data.entity.UserInfo

/**
 * @describe: 用户本地仓库
 * @date: 2020/1/4
 * @author: Mr Bai
 */
internal class UserLocal {
    /**
     * @describe: 保存或更新用户数据
     * @date: 2020/1/11
     */
   suspend fun saveOfUpdate(context: Context, userInfo: UserInfo) {

        val userDao = AppDatabase.getDatabase(context).userDao()

        if (!TextUtils.isEmpty(userInfo.userId)) {
            val loginUsers = userDao.queryLoginUsers()
            if(loginUsers.isEmpty()){
                val userInfoTable = UserInfoTable()
                userInfoTable.userId = userInfo.userId
                userInfoTable.fansNum = userInfo.fansNum
                userInfoTable.userName = userInfo.userName
                userInfoTable.nickName = userInfo.nickName
                userInfoTable.intro = userInfo.intro
                userInfoTable.avatar = userInfo.avatar
                userInfoTable.mobile = userInfo.mobile
                userInfoTable.sex = userInfo.sex
                userInfoTable.photo = userInfo.photo
                userInfoTable.birthday = userInfo.birthday
                userInfoTable.job = userInfo.job
                userInfoTable.colleges = userInfo.colleges
                userInfoTable.lat = userInfo.lat
                userInfoTable.lng = userInfo.lng
                userInfoTable.provinceCode = userInfo.provinceCode
                userInfoTable.provinceName = userInfo.provinceName
                userInfoTable.cityCode = userInfo.cityCode
                userInfoTable.cityName = userInfo.cityName
                userInfoTable.countyCode = userInfo.countyCode
                userInfoTable.countyName = userInfo.countyName
                userInfoTable.fullAddress = userInfo.fullAddress
                userInfoTable.praiseNum = userInfo.praiseNum
                userInfoTable.likeNum = userInfo.likeNum
                userInfoTable.fansNum = userInfo.fansNum
                userInfoTable.storeId = userInfo.storeId
                userInfoTable.access_token = userInfo.access_token
                userInfoTable.refresh_token = userInfo.refresh_token
                userInfoTable.firstLogin = userInfo.isFirst
                userInfoTable.loginState = userInfo.isLogin
                userInfoTable.registerDate = userInfo.registerDate

                userDao.insert(userInfoTable)
            }else{
                val userInfoTable = loginUsers[0]
                userInfoTable.userId = userInfo.userId
                userInfoTable.fansNum = userInfo.fansNum
                userInfoTable.userName = userInfo.userName
                userInfoTable.nickName = userInfo.nickName
                userInfoTable.intro = userInfo.intro
                userInfoTable.avatar = userInfo.avatar
                userInfoTable.mobile = userInfo.mobile
                userInfoTable.sex = userInfo.sex
                userInfoTable.photo = userInfo.photo
                userInfoTable.birthday = userInfo.birthday
                userInfoTable.job = userInfo.job
                userInfoTable.colleges = userInfo.colleges
                userInfoTable.lat = userInfo.lat
                userInfoTable.lng = userInfo.lng
                userInfoTable.provinceCode = userInfo.provinceCode
                userInfoTable.provinceName = userInfo.provinceName
                userInfoTable.cityCode = userInfo.cityCode
                userInfoTable.cityName = userInfo.cityName
                userInfoTable.countyCode = userInfo.countyCode
                userInfoTable.countyName = userInfo.countyName
                userInfoTable.fullAddress = userInfo.fullAddress
                userInfoTable.praiseNum = userInfo.praiseNum
                userInfoTable.likeNum = userInfo.likeNum
                userInfoTable.fansNum = userInfo.fansNum
                userInfoTable.storeId = userInfo.storeId
                userInfoTable.access_token = userInfo.access_token
                userInfoTable.refresh_token = userInfo.refresh_token
                userInfoTable.firstLogin = userInfo.isFirst
                userInfoTable.loginState = userInfo.isLogin
                userInfoTable.registerDate = userInfo.registerDate

                userDao.updateLoginUser(userInfoTable)
            }
        }
        AppDatabase.getDatabase(context).close()
    }

    suspend fun getLoginUserInfo(context: Context): UserInfo{
        val userInfo = UserInfo()
        val userDao = AppDatabase.getDatabase(context).userDao()
        val loginUsers = userDao.queryLoginUsers()
        if(loginUsers.isNotEmpty()){
            val userInfoTable = loginUsers[0]
            userInfo.userId = userInfoTable.userId
            userInfo.fansNum = userInfoTable.fansNum
            userInfo.userName = userInfoTable.userName
            userInfo.nickName = userInfoTable.nickName
            userInfo.intro = userInfoTable.intro
            userInfo.avatar = userInfoTable.avatar
            userInfo.mobile = userInfoTable.mobile
            userInfo.sex = userInfoTable.sex
            userInfo.photo = userInfoTable.photo
            userInfo.birthday = userInfoTable.birthday
            userInfo.job = userInfoTable.job
            userInfo.colleges = userInfoTable.colleges
            userInfo.lat = userInfoTable.lat
            userInfo.lng = userInfoTable.lng
            userInfo.provinceCode = userInfoTable.provinceCode
            userInfo.provinceName = userInfoTable.provinceName
            userInfo.cityCode = userInfoTable.cityCode
            userInfo.cityName = userInfoTable.cityName
            userInfo.countyCode = userInfoTable.countyCode
            userInfo.countyName = userInfoTable.countyName
            userInfo.fullAddress = userInfoTable.fullAddress
            userInfo.praiseNum = userInfoTable.praiseNum
            userInfo.likeNum = userInfoTable.likeNum
            userInfo.fansNum = userInfoTable.fansNum
            userInfo.storeId = userInfoTable.storeId
            userInfo.access_token = userInfoTable.access_token
            userInfo.refresh_token = userInfoTable.refresh_token
            userInfo.isFirst = userInfoTable.firstLogin
            userInfo.isLogin = userInfoTable.loginState
            userInfo.registerDate = userInfoTable.registerDate
        }
        AppDatabase.getDatabase(context).close()
        return userInfo
    }

//
    //    public UserInfo getUserInfo() {
    //        UserInfo userInfo = MemoryCacheRepository.getInstance().getUserInfo();
    //        if(userInfo==null){
    //            try {
    //                List<UserInfo> userInfos = LitePal.where("isLogin=?", String.valueOf(UserInfo.LoginStatus.LOGIN)).find(UserInfo.class);
    //                if(userInfos!=null && !userInfos.isEmpty()){
    //                    userInfo = userInfos.get(0);
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //                userInfo = new UserInfo();
    //            }
    //        }
    //        LogUtils.i("sql", GsonUtils.toJson(userInfo));
    //        return userInfo;
    //    }
    //
    //    /**
    //     * @describe: 退出登录
    //     * @date: 2020/1/16
    //     */
    //    public void logout(UserInfo userInfo) {
    //        if(userInfo!=null){
    //            LogUtils.i("sql="+GsonUtils.toJson(userInfo));
    //            userInfo.setIsLogin(UserInfo.LoginStatus.LOGOUT);
    //            userInfo.setAccess_token("");
    //            userInfo.setRefresh_token("");
    //            userInfo.saveOrUpdate("userName=?",userInfo.getUserName());
    //            MemoryCacheRepository.getInstance().clear();
    //        }
    //    }
}