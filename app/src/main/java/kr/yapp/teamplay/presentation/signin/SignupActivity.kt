package kr.yapp.teamplay.presentation.signin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_signup_nickname.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivitySignupBinding
import kr.yapp.teamplay.presentation.myteam.MyTeamSelectActivity

class SignupActivity : AppCompatActivity() {
    private val signupViewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }

    private lateinit var binding : ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, SignupEmailFragment()).commit()
        transStatusWhiteTextBar()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = signupViewModel
    }

    fun goToSignupPassword() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SignupPasswordFragment()).addToBackStack(null).commit()
    }

    fun goToSignupNickname() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SignupNicknameFragment()).addToBackStack(null).commit()
    }

    fun goToMain() {
        val intent = Intent(this, MyTeamSelectActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}
