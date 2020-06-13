package kr.yapp.teamplay.domain.entity.teammain

import kr.yapp.teamplay.domain.entity.UserRole

data class ClubInfo(
    val tag: String = "우리동네대표",
    val name: String = "",
    val location: String = "",
    val createDate: String = "",
    val memberCount: String = "",
    val userRole: UserRole = UserRole.ADMIN,
    val clubId: Int = -1
)
