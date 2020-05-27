package kr.yapp.teamplay.domain.entity.teammain

import java.io.Serializable

data class ResultItem(
    val createTime: String = "",
    val teamName: String = "",
    val win: Boolean
) : Serializable
