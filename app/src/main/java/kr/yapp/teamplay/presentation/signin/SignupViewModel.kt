package kr.yapp.teamplay.presentation.signin

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.data.SharedPreferenceManager
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.entity.ConstValue
import kr.yapp.teamplay.domain.usecase.SignupUsecase
import kr.yapp.teamplay.presentation.BaseViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import kr.yapp.teamplay.presentation.util.sha256
import kr.yapp.teamplay.util.PreferenceManager

class SignupViewModel(
    private val signupUsecase : SignupUsecase =
        SignupUsecase(AuthRepositoryImpl())
) : BaseViewModel() {
    val signUpEmailClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpEmailFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpSuccessFinish : SingleLiveEvent<Void> = SingleLiveEvent()

    val signUpEmailError : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordError : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameError : SingleLiveEvent<Void> = SingleLiveEvent()

    val alreadyRegisteredNickname : SingleLiveEvent<Void> = SingleLiveEvent()
    val alreadyRegisteredEmail : SingleLiveEvent<Void> = SingleLiveEvent()

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
                    signUpSuccessFinish.call()
                    PreferenceManager.setTokenKey(TeamPlayApplication.appContext, it.accessToken.token)
                    PreferenceManager.setRefreshTokenKey(TeamPlayApplication.appContext, it.refreshToken)
                    PreferenceManager.setUserId(TeamPlayApplication.appContext, it.userInfo.id.toString())
//                    SharedPreferenceManager.setPref(
//                        ConstValue.CONST_ACCESS_TOKEN, it.accessToken.token)
//                    SharedPreferenceManager.setPref(
//                        ConstValue.CONST_USER_ID, it.userInfo.id)
//                    SharedPreferenceManager.setPref(
//                        ConstValue.CONST_REFRESH_TOKEN, it.refreshToken)
                }, { e ->
                    if (e.localizedMessage == "email is already registered") {
                        alreadyRegisteredEmail.call()
                    } else if (e.localizedMessage == "email is already registered") {
                        alreadyRegisteredNickname.call()
                    }
                })
                .addDisposable()
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
