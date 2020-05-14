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
import kotlinx.android.synthetic.main.activity_signin.*
import kotlinx.android.synthetic.main.fragment_signin_email.*
import kotlinx.android.synthetic.main.fragment_signin_password.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivitySigninBinding
import kr.yapp.teamplay.presentation.myteam.MyTeamSelectActivity

class SigninActivity : AppCompatActivity() {

    private val signinViewModel: SigninViewModel by lazy {
        ViewModelProvider(this).get(SigninViewModel::class.java)
    }

    private lateinit var binding: ActivitySigninBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, SigninEmailFragment()).commit()
        setLiveDataObserver()
        transStatusWhiteTextBar()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        binding.lifecycleOwner = this
        binding.viewModel = signinViewModel
    }

    private fun setLiveDataObserver() {
        signinViewModel.signInEmailClick.observe(this, Observer {
            signinViewModel.setSigninEmail(et_signin_email.text.toString())
            signinViewModel.checkAlreadyUser()
        })

        signinViewModel.signInStart.observe(this, Observer {
            goToSigninPassword()
            btn_next.visibility = View.INVISIBLE
            btn_signin_finish.visibility = View.VISIBLE
        })

        signinViewModel.signInPasswordClick.observe(this, Observer {
            signinViewModel.checkEmailPassword()
        })

        /*
        success Signin
         */
        signinViewModel.signInSuccess.observe(this, Observer {
            goToMain()
        })

        /*
        enter an unregistered email
         */
        signinViewModel.signUpStart.observe(this, Observer {
            goToSignupPage()
        })

        signinViewModel.signInEmailError.observe(this, Observer {
            signinViewModel.setSigninPassword(et_signin_password.text.toString())
            setErrorSigninEmail()
        })

        signinViewModel.signInPasswordError.observe(this, Observer {
            setErrorSigninPassword()
        })
    }

    private fun goToSigninPassword() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SigninPasswordFragment()).commit()
    }

    private fun goToMain() {
        val intent = Intent(this, MyTeamSelectActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToSignupPage() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setErrorSigninEmail() {
        signin_email_errorMessage.text = resources.getText(R.string.signin_error_email)
        input_signin_email.background.setTint(ContextCompat.getColor(this, R.color.colorRed))
    }

    private fun setErrorSigninPassword() {
        signin_password_errorMessage.text = resources.getText(R.string.signin_error_password)
        et_signin_password.background.setTint(ContextCompat.getColor(this, R.color.colorRed))
    }

    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

}
