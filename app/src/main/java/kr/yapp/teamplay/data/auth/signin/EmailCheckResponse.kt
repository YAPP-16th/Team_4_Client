package kr.yapp.teamplay.data.auth.signin

import com.google.gson.annotations.SerializedName

data class EmailCheckResponse(
    @SerializedName("message") val message : String,
    @SerializedName("possible") val possible : Boolean
)
