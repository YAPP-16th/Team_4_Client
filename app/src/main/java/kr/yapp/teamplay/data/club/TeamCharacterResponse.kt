/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.data.club

import com.google.gson.annotations.SerializedName
import kr.yapp.teamplay.domain.entity.TeamCharacter as Entity

data class TeamCharacterResponse(
    @SerializedName("characters")
    val characters: List<String>
)

fun TeamCharacterResponse.toEntity(): List<Entity> =
    characters.map { value -> Entity.valueOf(value) }
