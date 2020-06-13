/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.domain.entity

data class MyTeam(
    val id: String = "-1",
    val category: String = "BASKETBALL",
    val teamName: String = "상암동 농구클럽",
    val teamLocation: String = "서울시 마포구 우리기술사옥",
    val since: String = "2020.04.30",
    val userCount: Int = 30,
    val isCreateCard: Boolean = false
)
