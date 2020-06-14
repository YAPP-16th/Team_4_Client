/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.domain.entity.matchresult

data class MatchIndividualScore(
    val matchResultType: String,
    val receiver: String,
    val score: Int
)
