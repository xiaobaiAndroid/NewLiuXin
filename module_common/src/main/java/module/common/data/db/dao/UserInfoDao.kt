package module.common.data.db.dao

import androidx.room.*
import module.common.data.db.entity.UserInfoTable
import module.common.data.entity.UserInfo.LoginStatus

/**
 *@author: baizf
 *@date: 2023/3/7
 */
@Dao
interface UserInfoDao {


    @Query("SELECT * FROM user_table WHERE loginState = :loginState")
    suspend fun queryLoginUsers(loginState: Int = LoginStatus.LOGIN): List<UserInfoTable>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Update
    suspend fun updateLoginUser(userInfoTable: UserInfoTable)

    @Insert
    suspend fun insert(userInfoTable: UserInfoTable)

}