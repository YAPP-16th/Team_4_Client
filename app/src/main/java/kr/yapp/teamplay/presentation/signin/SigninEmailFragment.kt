package kr.yapp.teamplay.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_signin_email.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSigninEmailBinding

class SigninEmailFragment(signinViewModel: SigninViewModel) : Fragment() {
    private val signinViewModel : SigninViewModel
    private lateinit var binding: FragmentSigninEmailBinding
    private lateinit var mActivity: SigninActivity

    init {
        this.signinViewModel = signinViewModel
    }

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
            R.layout.fragment_signin_email, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = signinViewModel

        mActivity = activity as SigninActivity
    }

    private fun setLiveDataObserver() {
        signinViewModel.signInEmailClick.observe(viewLifecycleOwner, Observer {
            signinViewModel.setSigninEmail(et_signin_email.text.toString())
            signinViewModel.checkRegisteredUser()
        })

        signinViewModel.signInEmailError.observe(viewLifecycleOwner, Observer {
            setErrorSigninEmail()
        })

        signinViewModel.signInStart.observe(viewLifecycleOwner, Observer {
            mActivity.goToSigninPassword()
        })

        signinViewModel.signUpStart.observe(viewLifecycleOwner, Observer {
            mActivity.goToSignupPage()
        })
    }

    private fun setErrorSigninEmail() {
        signin_email_errorMessage.text = resources.getText(R.string.signin_error_email)
        et_signin_email.background.setTint(ContextCompat.getColor(mActivity, R.color.colorRed))
    }
}