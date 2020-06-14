/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.data.match

import com.google.gson.annotations.SerializedName

data class MatchDetailedResultResponse(

    @SerializedName("guestName")
    val guestName: String,

    @SerializedName("hostName")
    val hostName: String,

    @SerializedName("matchDetailResultScore")
    val matchDetailResultScore: MatchDetailResultScore
) {

    data class MatchDetailResultScore(

        @SerializedName("guestScore")
        val guestScore: Int,

        @SerializedName("hostScore")
        val hostScore: Int,

        @SerializedName("matchResultType")
        val matchResultType: String
    )
}
