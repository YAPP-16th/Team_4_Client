package kr.yapp.teamplay.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_signup_password.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSignupPasswordBinding

class SignupPasswordFragment(signupViewModel: SignupViewModel) : Fragment() {
    private val signupViewModel: SignupViewModel = signupViewModel
    private lateinit var binding: FragmentSignupPasswordBinding
    private lateinit var mActivity: SignupActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setDataBinding(inflater, container)
        setLiveDataObserver()

        return binding.root
    }

    private fun setDataBinding(inflater: LayoutInflater
                               , container: ViewGroup?) {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_signup_password, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = signupViewModel

        mActivity = activity as SignupActivity
    }

    private fun setLiveDataObserver() {
        signupViewModel.signUpPasswordClick.observe(viewLifecycleOwner, Observer {
            signupViewModel.setSignupPassword(et_signup_password.text.toString())
            signupViewModel.checkCorrectPassword()
        })

        signupViewModel.signUpPasswordFinish.observe(viewLifecycleOwner, Observer {
            mActivity.goToSignupNickname()
        })

        signupViewModel.signUpPasswordError.observe(this, Observer {
            setErrorSignupPassword()
        })
    }

    private fun setErrorSignupPassword() {
        signup_password_errorMessage.text = resources.getText(R.string.signup_error_password)
        et_signup_password.background.setTint(ContextCompat.getColor(mActivity, R.color.colorRed))
    }
}
