package kr.yapp.teamplay.presentation.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_signin_password.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSigninPasswordBinding

class SigninPasswordFragment(signinViewModel: SigninViewModel) : Fragment() {
    private val signinViewModel: SigninViewModel
    private lateinit var binding: FragmentSigninPasswordBinding
    private lateinit var mActivity: SigninActivity

    init {
        this.signinViewModel = signinViewModel
    }

    private val signinViewModel: SigninViewModel by lazy {
        ViewModelProvider(this).get(SigninViewModel::class.java)
    }

    private lateinit var mActivity: SigninActivity

    private val signinViewModel: SigninViewModel by lazy {
        ViewModelProvider(this).get(SigninViewModel::class.java)
    }

    private lateinit var mActivity: SigninActivity

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
            R.layout.fragment_signin_password, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = signinViewModel

        mActivity = activity as SigninActivity
    }

    private fun setLiveDataObserver() {
        signinViewModel.signInPasswordClick.observe(viewLifecycleOwner, Observer {
            signinViewModel.checkEmailPassword()
        })

        signinViewModel.signInPasswordError.observe(viewLifecycleOwner, Observer {
            setErrorSigninPassword()
        })

        signinViewModel.signInSuccess.observe(viewLifecycleOwner, Observer {
            mActivity.goToMain()
        })
    }

    private fun setErrorSigninPassword() {
        signin_password_errorMessage.text = resources.getText(R.string.signin_error_password)
        et_signin_password.background.setTint(ContextCompat.getColor(mActivity, R.color.colorRed))
    }
}
