/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.data.club

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.ClubJoinInfo
import kr.yapp.teamplay.domain.entity.TeamCharacter

data class GetClubJoinInfoResponse(

    @SerializedName("simpleClubInfo")
    val simpleClubInfo: SimpleClubInfo,

    @SerializedName("questions")
    val questions: List<String>,

    @SerializedName("message")
    val message: String
) {

    data class SimpleClubInfo(

        @SerializedName("characters")
        val characters: List<TeamCharacter> = listOf(),

        @SerializedName("createData")
        val createData: String = "",

        @SerializedName("location")
        val location: String = "",

        @SerializedName("memberCount")
        val memberCount: Int = 0,

        @SerializedName("name")
        val teamName: String = ""
    )
}

fun GetClubJoinInfoResponse.toEntity(): ClubJoinInfo =
    ClubJoinInfo(
        character = simpleClubInfo.characters,
        createDate = simpleClubInfo.createData,
        location = simpleClubInfo.location,
        memberCount = simpleClubInfo.memberCount,
        teamName = simpleClubInfo.teamName,
        questions = questions,
        message = message
    )
