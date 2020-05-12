package kr.yapp.teamplay.data.auth.signin

import com.google.gson.annotations.SerializedName

data class SigninRequest(
    @SerializedName("email") val email : String,
    @SerializedName("hashedPassword") val hashedPassword : String
)
