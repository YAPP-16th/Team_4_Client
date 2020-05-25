package kr.yapp.teamplay.presentation.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.usecase.SignupUsecase
import kr.yapp.teamplay.presentation.util.HashingPassword
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import kr.yapp.teamplay.presentation.util.sha256

class SignupViewModel(
    private val signupUsecase : SignupUsecase =
        SignupUsecase(AuthRepositoryImpl())
) : ViewModel() {
    val signUpEmailClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpEmailFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameFinish : SingleLiveEvent<Void> = SingleLiveEvent()

    val signUpEmailError : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordError : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameError : SingleLiveEvent<Void> = SingleLiveEvent()

    val signupEmail : MutableLiveData<String> = MutableLiveData()
    val signupPassword : MutableLiveData<String> = MutableLiveData()
    val signupNickname : MutableLiveData<String> = MutableLiveData()

    fun clickSignUpEmailButton() {
        signUpEmailClick.call()
    }

    fun clickSignUpPasswordButton() {
        signUpPasswordClick.call()
    }

    fun clickSignUpNicknameButton() {
        signUpNicknameClick.call()
    }

    fun checkCorrectEmail() {
        //유효한 이메일인지 체크
        val emailRegExp = "^[a-zA-Z0-9._%^-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        val matchResult = emailRegExp.matches(signupEmail.value.toString())

        if (matchResult) {
            signUpEmailFinish.call()
        } else {
            signUpEmailError.call()
        }
    }

    fun checkCorrectPassword() {
        //유효한 패스워드인지 체크
        val passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$".toRegex()
        val matchResult = passwordRegExp.matches(signupPassword.value.toString())

        if (matchResult) {
            signUpPasswordFinish.call()
        } else {
            signUpPasswordError.call()
        }
    }

    fun checkCorrectNickname() {
        //유효한 닉네임인지 체크
        val nicknameRegExp = "^[a-zA-Z0-9가-힣].{2,16}$".toRegex()
        val matchResult = nicknameRegExp.matches(signupNickname.value.toString())

        if (matchResult) {
            val email = signupEmail.value.toString()
            val hashedPassword = signupPassword.value.toString().sha256()
            signupUsecase.doSignup(email, signupNickname.value.toString(), hashedPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    signUpNicknameFinish.call()
                }, {
                    it.localizedMessage
                })
        } else {
            signUpNicknameError.call()
        }
    }

    fun setSignupEmail(email : String) {
        signupEmail.value = email
    }

    fun setSignupPassword(password : String) {
        signupPassword.value = password
    }

    fun setSignupNickname(nickname : String) {
        signupNickname.value = nickname
    }
}
