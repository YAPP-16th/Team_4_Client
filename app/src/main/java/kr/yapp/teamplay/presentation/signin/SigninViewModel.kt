package kr.yapp.teamplay.presentation.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.usecase.EmailCheckUsecase
import kr.yapp.teamplay.domain.usecase.SigninUsecase
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import kr.yapp.teamplay.presentation.util.sha256

class SigninViewModel(
    private val signinUsecase: SigninUsecase =
        SigninUsecase(AuthRepositoryImpl()),
    private val emailCheckUsecase : EmailCheckUsecase =
        EmailCheckUsecase(AuthRepositoryImpl())
) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()

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

    // check that an email is registered or not
    fun checkRegisteredUser(){
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
                    Log.d("MyTag", it.localizedMessage!!)
                })
                .addTo(compositeDisposable)
        } else {
            signInEmailError.call()
        }
    }

    fun submitEmailPassword() {
        val passwordRegExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[0-9]).{8,20}$".toRegex()
        val matchResult = passwordRegExp.matches(signinPassword.value.toString())

        if (matchResult) {
            val hashedPassword = signinPassword.value.toString().sha256()
            signinUsecase.doSignin(signinEmail.value.toString(), hashedPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    signInSuccess.call()
                }, {
                    signInPasswordError.call()
                })
                .addTo(compositeDisposable)
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