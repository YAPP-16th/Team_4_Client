package kr.yapp.teamplay.data.teammain.response

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.data.search.ClubListInfo

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
data class GetClubResponse(

    @SerializedName("resultCount")
    val resultCount: Int = 0,

    @SerializedName("clubListInfo")
    val list: List<ClubListInfo>? = null,

    @SerializedName("totalPage")
    val totalPage: Int = 0,

    @SerializedName("currentPage")
    val currentPage: Int = 0
)
