package kr.yapp.teamplay.presentation.onboarding

import androidx.lifecycle.ViewModel
import kr.yapp.teamplay.presentation.util.SingleLiveEvent

class OnboardingViewModel : ViewModel(){
    val kakaoLoginClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val naverLoginClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val googleLoginClick : SingleLiveEvent<Void> = SingleLiveEvent()
    val emailLoginClick : SingleLiveEvent<Void> = SingleLiveEvent()

    fun clickKakaoLoginButton(){
        kakaoLoginClick.call()
    }

    fun clickNaverLoginButton(){
        naverLoginClick.call()
    }

    fun clickGoogleLoginButton(){
        googleLoginClick.call()
    }

    fun clickEmailLoginButton(){
        emailLoginClick.call()
    }
}