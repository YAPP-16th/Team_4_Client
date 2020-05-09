package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.EmailCheckResponse

class EmailCheckUsecase(
    private val repository : AuthRepositoryImpl
) {

    fun doEmailCheck(email : String) : Single<EmailCheckResponse> =
        repository.signInEmailCheck(email)
}
