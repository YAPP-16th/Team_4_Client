package kr.yapp.teamplay.data.auth.signin

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.UserInfo

data class SigninResponse (
    @SerializedName("accessToken") val accessToken : String,
    @SerializedName("message") val message : String,
    @SerializedName("refreshToken") val refreshToken : String,
    @SerializedName("userInfo") val userInfo: UserInfo
)
