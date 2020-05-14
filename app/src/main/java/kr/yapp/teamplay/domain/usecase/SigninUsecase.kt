package kr.yapp.teamplay.domain.usecase

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.data.auth.signin.SigninResponse
import kr.yapp.teamplay.domain.repository.AuthRepository

class SigninUsecase(
    private val repository : AuthRepository = AuthRepositoryImpl()
) {

    fun doSignin(email : String, password : String) : Single<SigninResponse> =
        repository.signInByEmailRequest(email, password)
}
