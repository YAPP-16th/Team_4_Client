/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.domain.entity

data class ClubJoinInfo(
    val character: List<TeamCharacter>,
    val createDate: String,
    val location: String,
    val memberCount: Int,
    val teamName: String,
    val questions: List<String>
)
