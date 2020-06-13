/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.domain.entity

data class ClubJoinInfo(
    val teamName: String,
    val character: List<TeamCharacter>,
    val createDate: String,
    val location: String,
    val memberCount: Int,
    val message: String,
    val questions: List<String>
)
