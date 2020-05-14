package kr.yapp.teamplay.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityOnboardingBinding
import kr.yapp.teamplay.presentation.signin.SigninActivity

class OnboardingActivity : AppCompatActivity() {

    private val viewModel: OnboardingViewModel by lazy {
        ViewModelProvider(this).get(OnboardingViewModel::class.java)
    }
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setObserveLiveData()

    }

    private fun setObserveLiveData() {
        viewModel.kakaoLoginClick.observe(this, Observer {
            goToSignIn()
        })

        viewModel.naverLoginClick.observe(this, Observer {
            goToSignIn()
        })

        viewModel.googleLoginClick.observe(this, Observer {
            goToSignIn()
        })

        viewModel.emailLoginClick.observe(this, Observer {
            goToSignIn()
        })
    }

    fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    fun goToSignIn() {
        intent = Intent(this, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
}
