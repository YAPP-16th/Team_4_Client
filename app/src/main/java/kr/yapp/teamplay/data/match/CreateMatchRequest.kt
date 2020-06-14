package kr.yapp.teamplay.data.match

data class CreateMatchRequest (
    val requesterClubId : Long,
    val requesterUserId : Long,
    val contact : String
)