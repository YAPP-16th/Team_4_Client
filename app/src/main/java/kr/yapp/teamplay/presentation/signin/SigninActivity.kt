package kr.yapp.teamplay.presentation.signin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentTransaction
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
        transStatusWhiteTextBar()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        binding.lifecycleOwner = this
        binding.viewModel = signinViewModel
    }

    fun goToMain() {
        val intent = Intent(this, MyTeamSelectActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToSignupPage() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToSigninPassword() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.fragment_close_enter, R.anim.fragment_open_exit)
            .replace(R.id.fragment_container, SigninPasswordFragment())
            .addToBackStack(null).commit()
    }

    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

}
