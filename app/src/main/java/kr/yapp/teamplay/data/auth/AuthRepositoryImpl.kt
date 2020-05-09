package kr.yapp.teamplay.data.auth

import io.reactivex.Single
import kr.yapp.teamplay.data.*
import kr.yapp.teamplay.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authService: AuthService =
        RetrofitManager.getRetrofit().create(
            AuthService::class.java)
) : AuthRepository {
    override fun signInByEmailRequest(email : String, hashedPassword : String): Single<SigninResponse> {
        val signinRequest =
            SigninRequest(email, hashedPassword)
        return authService.signInByEmailRequest(signinRequest)
    }

    override fun signUpByEmailRequest(email: String, nickname : String, hashedPassword: String): Single<SignupResponse> {
        val signupRequest =
            SignupRequest(
                email,
                nickname,
                hashedPassword
            )
        return authService.signUpByEmailRequest(signupRequest)
    }

    override fun signInEmailCheck(email: String): Single<EmailCheckResponse> {
        return authService.signInEmailCheck(email)
    }

}
