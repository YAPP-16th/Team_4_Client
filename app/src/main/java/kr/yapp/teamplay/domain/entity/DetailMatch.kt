package kr.yapp.teamplay.domain.entity

data class DetailMatch(
    val detailTitle : String = "저희와 함께 즐거운 매치",
    val hostTeam : String = "상암동 농구 클럽",
    val matchDay : String = "2020.04.30",
    val startTime : String = "14:00",
    val endTime : String = "17:00",
    val location : String = "여의도 한강공원 농구코트3",
    val gameType : String = "3대3 반코트",
    val introduce : String = "상암동 대표 농구 동호회 상암동 농구클럽입니다."
)