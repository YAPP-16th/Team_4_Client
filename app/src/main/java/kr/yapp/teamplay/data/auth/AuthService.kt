package kr.yapp.teamplay.data.auth

import io.reactivex.Single
import kr.yapp.teamplay.data.auth.signin.EmailCheckResponse
import kr.yapp.teamplay.data.auth.signin.SigninRequest
import kr.yapp.teamplay.data.auth.signin.SigninResponse
import kr.yapp.teamplay.data.auth.signup.SignupRequest
import kr.yapp.teamplay.data.auth.signup.SignupResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthService {

    @POST("/v1/auth/sign-in")
    fun signInByEmailRequest(@Body signInByEmailRequest : SigninRequest) : Single<SigninResponse>

    @POST("/v1/auth/sign-up")
    fun signUpByEmailRequest(@Body signupRequest: SignupRequest) : Single<SignupResponse>

    @GET("/v1/auth/exist/email/{email}")
    fun signInEmailCheck(@Path("email") email : String) : Single<EmailCheckResponse>
}
