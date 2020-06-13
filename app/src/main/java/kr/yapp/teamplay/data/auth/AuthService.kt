package kr.yapp.teamplay.data.auth

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.signin.EmailCheckResponse
import kr.yapp.teamplay.data.auth.signin.SigninRequest
import kr.yapp.teamplay.data.auth.signin.SigninResponse
import kr.yapp.teamplay.data.auth.signup.SignupRequest
import kr.yapp.teamplay.data.auth.signup.SignupResponse
import kr.yapp.teamplay.domain.entity.AccessToken
import retrofit2.http.*

interface AuthService {
    @GET("/v1/auth/access-token")
    fun requestAccessToken(@Header("refreshToken") refreshToken : String) : Single<AccessToken>

    @POST("/v1/auth/sign-in")
    fun signInByEmailRequest(@Body signInByEmailRequest : SigninRequest) : Single<SigninResponse>

    @POST("/v1/auth/sign-up")
    fun signUpByEmailRequest(@Body signupRequest: SignupRequest) : Single<SignupResponse>

    @GET("/v1/auth/exist/email/{email}")
    fun signInEmailCheck(@Path("email") email : String) : Single<EmailCheckResponse>
}
