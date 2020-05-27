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
import kotlinx.android.synthetic.main.fragment_signup_email.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSignupEmailBinding

class SignupEmailFragment(signupViewModel: SignupViewModel) : Fragment() {
    private val signupViewModel: SignupViewModel
    private lateinit var binding: FragmentSignupEmailBinding
    private lateinit var mActivity: SignupActivity

    init {
        this.signupViewModel = signupViewModel
    }

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
            R.layout.fragment_signup_email, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = signupViewModel

        mActivity = activity as SignupActivity

    }

    private fun setLiveDataObserver() {
        signupViewModel.signUpEmailClick.observe(viewLifecycleOwner, Observer {
            signupViewModel.setSignupEmail(et_signup_email.text.toString())
            signupViewModel.checkCorrectEmail()
        })

        signupViewModel.signUpEmailFinish.observe(viewLifecycleOwner, Observer {
            mActivity.goToSignupPassword()
        })

        signupViewModel.signUpEmailError.observe(viewLifecycleOwner, Observer {
            setErrorSignupEmail()
        })
    }

    private fun setErrorSignupEmail() {
        signup_email_errorMessage.text = resources.getText(R.string.signup_error_email)
        et_signup_email.background.setTint(ContextCompat.getColor(mActivity, R.color.colorRed))
    }
}
