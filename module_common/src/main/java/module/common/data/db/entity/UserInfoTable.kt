package module.common.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 *@author: baizf
 *@date: 2023/3/7
 */
@Entity(tableName = "user_table")
class UserInfoTable{

    @PrimaryKey
    var userId: String = ""


    var userName: String? = null


    var nickName: String? = null


    var intro: String? = null


    var avatar: String? = null


    var mobile: String? = null

    //性别，1男，2女
    var sex: Int = 0

    var photo: String? = null


    var birthday: String? = null


    var job: String? = null


    var colleges: String? = null


    var lat: String? = null


    var lng: String? = null


    var provinceCode: String? = null


    var provinceName: String? = null


    var cityCode: String? = null


    var cityName: String? = null


    var countyCode: String? = null


    var countyName: String? = null


    var fullAddress: String? = null


    var praiseNum: Int = 0


    var likeNum: Int = 0


    var fansNum: Int = 0


    var storeId: String? = null


    var access_token: String? = null


    var refresh_token: String? = null

    var firstLogin: Int = 0


    var loginState: Int = 0

    /*注册时间*/
    var registerDate: String? = null

}