package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.signin.EmailCheckResponse
import kr.yapp.teamplay.domain.repository.AuthRepository

class EmailCheckUsecase(
    private val repository : AuthRepository = AuthRepositoryImpl()
) {

    fun doEmailCheck(email : String) : Single<EmailCheckResponse> =
        repository.signInEmailCheck(email)
}
