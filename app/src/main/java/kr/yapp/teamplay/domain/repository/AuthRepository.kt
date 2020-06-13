package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.signin.EmailCheckResponse
import kr.yapp.teamplay.data.auth.signin.SigninResponse
import kr.yapp.teamplay.data.auth.signup.SignupResponse
import kr.yapp.teamplay.domain.entity.AccessToken

interface AuthRepository {
    fun requestAccessToken(refreshToken : String) : Single<AccessToken>

    fun signInByEmailRequest(email : String, hashedPassword : String) : Single<SigninResponse>

    fun signUpByEmailRequest(email: String, nickname : String, hashedPassword: String) : Single<SignupResponse>

    fun signInEmailCheck(email: String) : Single<EmailCheckResponse>
}
