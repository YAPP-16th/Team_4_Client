package kr.yapp.teamplay.presentation.signin

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.usecase.EmailCheckUsecase
import kr.yapp.teamplay.domain.usecase.SigninUsecase
import kr.yapp.teamplay.presentation.util.HashingPassword
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import okhttp3.ResponseBody
import retrofit2.HttpException

class SigninViewModel(
    private val signinUsecase: SigninUsecase =
        SigninUsecase(AuthRepositoryImpl()),
    private val emailCheckUsecase : EmailCheckUsecase =
        EmailCheckUsecase(AuthRepositoryImpl())
) : ViewModel(){

    val signInEmailClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signInPasswordClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signInStart : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpStart : SingleLiveEvent<Void> = SingleLiveEvent()
    val signInSuccess : SingleLiveEvent<Void> = SingleLiveEvent()

    val signInEmailError : SingleLiveEvent<Void> = SingleLiveEvent()
    val signInPasswordError : SingleLiveEvent<Void> = SingleLiveEvent()

    val signinEmail : MutableLiveData<String> = MutableLiveData()
    val signinPassword : MutableLiveData<String> = MutableLiveData()

    fun clickSignInEmailButton() {
        signInEmailClick.call()
    }

    fun clickSignInPasswordButton() {
        signInPasswordClick.call()
    }

    // check a email is alreadyUser of not User
    fun checkAlreadyUser(){
        val emailRegExp = "^[a-zA-Z0-9._%^-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        val matchResult = emailRegExp.matches(signinEmail.value.toString())

        if (matchResult) {
            emailCheckUsecase.doEmailCheck(signinEmail.value.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.possible) {
                        signInStart.call()
                    } else {
                        signUpStart.call()
                    }
                }, {
                    Log.d("MyTag", it.localizedMessage)
                })
        } else {
            signInEmailError.call()
        }
    }

    // signinEmailFragment의 email값까지 넘겨받아 API와 연결합니다.
    fun checkEmailPassword() {
        val passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$".toRegex()
        val matchResult = passwordRegExp.matches(signinPassword.value.toString())

        if (matchResult) {
            val hashingPassword = HashingPassword().hashString(signinPassword.value.toString(), "SHA-256")
            signinUsecase.doSignin(signinEmail.value.toString(), hashingPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //main 화면으로 간다
                    signInSuccess.call()
                }, {
                    //input password가 에러 표시나게 해야
                    val body: ResponseBody? = (it as HttpException).response()?.errorBody()
                    Log.d("MyTag", it.localizedMessage)
                })
        } else {
            signInPasswordError.call()
        }
    }

    fun setSigninEmail(email : String) {
        signinEmail.value = email
    }

    fun setSigninPassword(password : String) {
        signinPassword.value = password
    }
}
