package kr.yapp.teamplay.presentation.signin

import androidx.lifecycle.ViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class SigninViewModel() : ViewModel(){

    val signInEmailClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val signInPasswordClick : SingleLiveEvent<Void> = SingleLiveEvent()
    var signInStart : SingleLiveEvent<Void> = SingleLiveEvent()
    var signUpStart : SingleLiveEvent<Void> = SingleLiveEvent()
    var signInPasswordFinish : SingleLiveEvent<Void> = SingleLiveEvent()

    fun clickSignInEmailButton() {
        signInEmailClick.call()
    }

    fun clickSignInPasswordButton() {
        signInPasswordClick.call()
    }

    fun checkAlreadyUser(email : String){
        //회원 가입한 유저인지 판단합니다.
        signInStart.call()
        //doSignupCallback.call()
    }

    fun inputSignInPassword(password : String) {
        signInPasswordFinish.call()
    }
}