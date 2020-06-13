package kr.yapp.teamplay.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_splash.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.data.SharedPreferenceManager
import kr.yapp.teamplay.databinding.ActivitySplashBinding
import kr.yapp.teamplay.presentation.myteam.MyTeamSelectActivity
import kr.yapp.teamplay.presentation.signin.SigninActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    private val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        startSplashAnimation()
        setLiveDataObserver()
    }

    private fun startAutoSignin() {
        SharedPreferenceManager.init(this)
        viewModel.AutoLoginThread().run()
    }

    private fun setLiveDataObserver() {
        viewModel.startSigninActivity.observe(this, Observer {
            SigninActivity.start(this@SplashActivity)
            finish()
        })

        viewModel.startMyTeamActivity.observe(this, Observer {
            val intent = Intent(this, MyTeamSelectActivity::class.java)
            startActivity(intent)
            finish()
        })
    }

    private fun startSplashAnimation() {
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in)
        splash_logo.startAnimation(logoAnimation)

        logoAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                binding.splashProgress.visibility = View.VISIBLE
                startAutoSignin()
            }
        })
    }
}
