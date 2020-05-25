package kr.yapp.teamplay.data.match

import kr.yapp.teamplay.domain.entity.MatchInfo

data class MatchList(
    val description1 : String?,
    val description2 : String?,
    val matchInfo : MatchInfo,
    val title : String
)