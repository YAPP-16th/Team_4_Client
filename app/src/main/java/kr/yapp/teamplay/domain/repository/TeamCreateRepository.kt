package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.teamcreate.CreateClubRequest

interface TeamCreateRepository {
    fun teamCreate(accessToken: String?, createClubRequest: CreateClubRequest) : Single<Void>
}
