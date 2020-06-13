package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.entity.AccessToken
import kr.yapp.teamplay.domain.repository.AuthRepository

class GetAccessTokenUseCase(
    private val repository : AuthRepository = AuthRepositoryImpl()
) {
    fun requestAccessToken(refreshToken : String) : Single<AccessToken> =
        repository.requestAccessToken(refreshToken)
}