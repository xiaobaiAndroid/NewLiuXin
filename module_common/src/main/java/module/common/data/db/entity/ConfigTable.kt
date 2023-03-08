package module.common.data.db.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import module.common.type.LanguageType

/*
* @describe: app配置实体
* @author: bzf
* @date: 1/12/21
*/
@Entity(tableName = "config_table")
class ConfigTable {

    @PrimaryKey
    var id = "config"

    var language: Int = LanguageType.CN.value

    var firstLogin: Boolean = true

    var firstOpen: Boolean = true

}