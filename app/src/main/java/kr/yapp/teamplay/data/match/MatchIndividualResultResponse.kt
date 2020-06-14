/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.data.match

import com.google.gson.annotations.SerializedName

data class MatchIndividualResultResponse(

    @SerializedName("matchResultType")
    val matchResultType: String,

    @SerializedName("recevier")
    val receiver: String,

    @SerializedName("score")
    val score: Int
)
