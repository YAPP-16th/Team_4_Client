package kr.yapp.teamplay.data.teammain.response

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.teammain.ClubInfo
import java.io.Serializable

/**
 * @author ohjuntaek
 * @since 2020. 04. 17 Mon
 *
 */
data class TeamMainItemResponse(
    @SerializedName("simpleClubInfo") val clubInfo: ClubInfo,
    @SerializedName("feedCount") val feedCount: String,
    @SerializedName("simpleFeeds") val feedItemResponses: List<TeamMainFeedItemResponse>
) : Serializable