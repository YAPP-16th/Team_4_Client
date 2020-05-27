package kr.yapp.teamplay.domain.entity.teammain

import java.io.Serializable

data class NoticeItem(
    val title: String = "",
    val createTime: String = "",
    val content: String = ""
) : Serializable
