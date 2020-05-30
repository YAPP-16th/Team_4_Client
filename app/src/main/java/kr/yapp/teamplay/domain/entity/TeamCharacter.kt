/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.domain.entity

enum class TeamCharacter(val korean: String) {
    COAT3TO3("3대3"),
    COAT5TO5("5대5"),
    JUST_ENJOY("즐겜"),
    JUST_ABILITY("실력"),
    STUDENT("대학생"),
    WORKER("직장인"),
    DINING_TOGETHER("회식도"),
    JUST_BASKETBALL("농구만"),
    WEEKEND("주말"),
    WEEKDAY("주중")
}

fun TeamCharacter.toHashMap(): HashMap<String, String> =
    HashMap<String, String>().apply {
        TeamCharacter.values().map { character ->
            put(character.name, character.korean)
        }
    }
