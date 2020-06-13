package kr.yapp.teamplay.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.data.SharedPreferenceManager
import kr.yapp.teamplay.data.auth.AuthRepositoryImpl
import kr.yapp.teamplay.domain.entity.ConstValue
import kr.yapp.teamplay.domain.usecase.GetAccessTokenUseCase
import kr.yapp.teamplay.presentation.util.SingleLiveEvent
import kr.yapp.teamplay.util.PreferenceManager
import java.util.concurrent.TimeUnit

class SplashViewModel(
    private val getAccessTokenUseCase: GetAccessTokenUseCase =
        GetAccessTokenUseCase(AuthRepositoryImpl())
) : ViewModel() {
    val startSigninActivity : SingleLiveEvent<Void> = SingleLiveEvent()
    val startMyTeamActivity : SingleLiveEvent<Void> = SingleLiveEvent()

    inner class AutoLoginThread : Runnable {
        override fun run() {
            val refreshToken =
                PreferenceManager.getRefreshTokenKey(TeamPlayApplication.appContext)
                //SharedPreferenceManager.getStringPref(ConstValue.CONST_REFRESH_TOKEN)

            if (refreshToken != "") {
                Log.d("MyTag", refreshToken)
                getAccessTokenUseCase.requestAccessToken(refreshToken)
                    .delay(1000,TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        PreferenceManager.setTokenKey(TeamPlayApplication.appContext, it.token)
//                        SharedPreferenceManager.setPref(
//                            ConstValue.CONST_ACCESS_TOKEN, it.token)
                        startMyTeamActivity.call()
                    }, {
                        startSigninActivity.call()
                    })
            } else {
                Thread.sleep(2000)
                startSigninActivity.call()
            }
        }
    }
}