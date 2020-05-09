package kr.yapp.teamplay.presentation.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.usecase.SignupUsecase
import kr.yapp.teamplay.presentation.util.HashingPassword
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class SignupViewModel(
    private val signupUsecase : SignupUsecase = SignupUsecase(
        AuthRepositoryImpl()
    )
) : ViewModel() {
    val signUpEmailClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpEmailFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpPasswordFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signUpNicknameFinish : SingleLiveEvent<Void> = SingleLiveEvent()
    val signupEmail : MutableLiveData<String> = MutableLiveData()
    val signupPassword : MutableLiveData<String> = MutableLiveData()

    fun clickSignUpEmailButton() {
        signUpEmailClick.call()
    }

    fun clickSignUpPasswordButton() {
        signUpPasswordClick.call()
    }

    fun clickSignUpNicknameButton() {
        signUpNicknameClick.call()
    }

    fun inputSignUpEmail() {
        //유효한 이메일인지 체크
        signUpEmailFinish.call()
    }

    fun inputSignUpPassword() {
        //유효한 패스워드인지 체크
        signUpPasswordFinish.call()
    }

    fun inputSignUpNickname(nickname : String) {
        //유효한 닉네임인지 체크
        val email = signupEmail.value.toString()
        val hashedPassword = HashingPassword().hashString(signupPassword.value.toString(), "SHA-256")
        signupUsecase.doSignup(email, nickname, hashedPassword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                signUpNicknameFinish.call()
            }, {
                it.localizedMessage
            })
    }
}
