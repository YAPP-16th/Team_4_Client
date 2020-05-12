package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.signup.SignupResponse

class SignupUsecase (
    private val repository : AuthRepositoryImpl
) {

    fun doSignup(email : String, nickname : String, password : String) : Single<SignupResponse> =
        repository.signUpByEmailRequest(email, nickname, password)
}
