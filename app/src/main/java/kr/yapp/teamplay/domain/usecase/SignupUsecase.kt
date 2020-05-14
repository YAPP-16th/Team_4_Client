package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.signup.SignupResponse
import kr.yapp.teamplay.domain.repository.AuthRepository

class SignupUsecase (
    private val repository : AuthRepository = AuthRepositoryImpl()
) {

    fun doSignup(email : String, nickname : String, password : String) : Single<SignupResponse> =
        repository.signUpByEmailRequest(email, nickname, password)
}
