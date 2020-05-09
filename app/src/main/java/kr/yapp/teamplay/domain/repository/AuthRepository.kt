package kr.yapp.teamplay.domain.repository

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.EmailCheckResponse
import kr.yapp.teamplay.data.auth.SigninResponse
import kr.yapp.teamplay.data.auth.SignupResponse

interface AuthRepository {

    fun signInByEmailRequest(email : String, hashedPassword : String) : Single<SigninResponse>

    fun signUpByEmailRequest(email: String, nickname : String, hashedPassword: String) : Single<SignupResponse>

    fun signInEmailCheck(email: String) : Single<EmailCheckResponse>
}
