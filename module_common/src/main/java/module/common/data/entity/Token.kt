package module.common.data.entity

data class Token(
    val access_token: String,
    val levelId: Int,
    val mediaSize: Int,
    val mediaTime: Int,
    val refresh_token: String,
    val userId: String,
    val userName: String
)