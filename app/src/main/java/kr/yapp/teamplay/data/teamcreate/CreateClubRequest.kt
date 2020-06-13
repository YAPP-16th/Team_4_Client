package kr.yapp.teamplay.data.teamcreate

import kr.yapp.teamplay.domain.entity.TeamCharacter

data class CreateClubRequest(
    var name: String = "",
    var category: String = "BASKETBALL",
    var emblem: String = "",
    var location: String = "",
    var ability: String = "MIDDLE",
    var thumbnail: String = "",
    var introduce: String = "",
    var contact: String = "",
    var createTeamDate: String = "",
    var characters: List<TeamCharacter> = arrayListOf(TeamCharacter.COAT3TO3),
    var questions: List<String> = mutableListOf("")
)