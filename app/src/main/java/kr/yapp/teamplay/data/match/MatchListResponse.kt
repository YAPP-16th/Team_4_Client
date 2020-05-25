package kr.yapp.teamplay.data.match

import kr.yapp.teamplay.domain.entity.MatchInfo

data class MatchListResponse(
    val currentPage: Int,
    val filterTitle : String,
    val matchList: MutableList<MatchList>,
    val totalPage: Int
)