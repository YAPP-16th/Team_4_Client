package kr.yapp.teamplay.data.auth.signup

import com.google.gson.annotations.SerializedName

data class SignupRequest(
    @SerializedName("email") val email : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("hashedPassword") val hashedPassword : String
)
