package kr.yapp.teamplay.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.fragment_signup_email.*
import kotlinx.android.synthetic.main.fragment_signup_nickname.*
import kotlinx.android.synthetic.main.fragment_signup_password.*
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
        setLiveDataObserver()
    }

    fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        binding.lifecycleOwner = this
        binding.viewModel = signupViewModel
    }

    fun setLiveDataObserver() {
        signupViewModel.signUpEmailClick.observe(this, Observer {
            signupViewModel.setSignupEmail(et_signup_email.text.toString())
            signupViewModel.checkCorrectEmail()
        })

        signupViewModel.signUpEmailFinish.observe(this, Observer {
            goToSignupPassword()
            btn_signup_email.visibility = View.INVISIBLE
            btn_signup_password.visibility = View.VISIBLE
        })

        signupViewModel.signUpPasswordClick.observe(this, Observer {
            signupViewModel.setSignupPassword(et_signup_password.text.toString())
            signupViewModel.checkCorrectPassword()
        })

        signupViewModel.signUpPasswordFinish.observe(this, Observer {
            goToSignupNickname()
            btn_signup_password.visibility = View.INVISIBLE
            btn_signup_nickname.visibility = View.VISIBLE
        })

        signupViewModel.signUpNicknameClick.observe(this, Observer {
            signupViewModel.setSignupNickname(et_signup_nickname.text.toString())
            signupViewModel.checkCorrectNickname()
        })

        signupViewModel.signUpEmailError.observe(this, Observer {
            setErrorSignupEmail()
        })

        signupViewModel.signUpPasswordError.observe(this, Observer {
            setErrorSignupPassword()
        })

        signupViewModel.signUpNicknameError.observe(this, Observer {
            setErrorSignupNickname()
        })

        signupViewModel.signUpNicknameFinish.observe(this, Observer {
            goToMain()
        })
    }

    fun goToSignupPassword() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SignupPasswordFragment()).commit()
    }

    fun goToSignupNickname() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SignupNicknameFragment()).commit()
    }

    fun goToMain() {
        val intent = Intent(this, MyTeamSelectActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun setErrorSignupEmail() {
        signup_email_errorMessage.text = resources.getText(R.string.signup_error_email)
        et_signup_email.background.setTint(resources.getColor(R.color.colorRed))
    }

    fun setErrorSignupPassword() {
        signup_password_errorMessage.text = resources.getText(R.string.signup_error_password)
        et_signup_password.background.setTint(resources.getColor(R.color.colorRed))
    }

    fun setErrorSignupNickname() {
        signup_nickname_errorMessage.text = resources.getText(R.string.signup_error_nickname)
        et_signup_nickname.background.setTint(resources.getColor(R.color.colorRed))
    }
}