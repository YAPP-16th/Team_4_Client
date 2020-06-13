package kr.yapp.teamplay.data.myteam

import kr.yapp.teamplay.domain.entity.teammain.ClubInfo

data class MyTeamResponse(
    val clubsInfo: List<ClubInfo>
)