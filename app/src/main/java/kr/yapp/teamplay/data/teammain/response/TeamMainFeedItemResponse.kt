package kr.yapp.teamplay.data.teammain.response

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.teammain.NoticeItem
import kr.yapp.teamplay.domain.entity.teammain.ResultItem
import java.io.Serializable

data class TeamMainFeedItemResponse(
    @SerializedName("type") val type: Int,
    @SerializedName("simpleResultInfo") val resultItem: ResultItem?,
    @SerializedName("simpleNoticeInfo") val noticeItem: NoticeItem?
) : Serializable
