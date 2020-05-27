package kr.yapp.teamplay.data.search

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.ClubListInfo as Entity

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
data class ClubListInfo(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("location")
    val location: String,
    @SerializedName("memberCount")
    val memberCount: Int = 0
)

fun ClubListInfo.toEntity() = Entity(
    id = id,
    name = name,
    location = location,
    memberCount = memberCount
)
