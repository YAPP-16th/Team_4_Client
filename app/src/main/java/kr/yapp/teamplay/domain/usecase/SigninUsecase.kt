package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.signin.SigninResponse

class SigninUsecase(
    private val repository : AuthRepositoryImpl
) {

    fun doSignin(email : String, password : String) : Single<SigninResponse> =
        repository.signInByEmailRequest(email, password)
}
