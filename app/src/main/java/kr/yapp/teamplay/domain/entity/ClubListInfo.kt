package kr.yapp.teamplay.domain.entity

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
data class ClubListInfo(
    val id: Int = 0,
    val name: String = "",
    val location: String,
    val memberCount: Int = 0
)
