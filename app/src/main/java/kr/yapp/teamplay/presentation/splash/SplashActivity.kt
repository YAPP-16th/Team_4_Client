package kr.yapp.teamplay.presentation.splash

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_splash.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivitySplashBinding
import kr.yapp.teamplay.presentation.signin.SigninActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_fade_in)
        splash_logo.startAnimation(logoAnimation)

        logoAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
            override fun onAnimationEnd(animation: Animation?) {
                SigninActivity.start(this@SplashActivity)
                finish()
            }

        })
    }
}