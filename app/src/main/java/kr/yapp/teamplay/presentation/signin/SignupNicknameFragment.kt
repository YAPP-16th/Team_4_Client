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
import kotlinx.android.synthetic.main.fragment_signup_nickname.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSignupNicknameBinding

class SignupNicknameFragment : Fragment() {
    private val signupViewModel: SignupViewModel by lazy {
        ViewModelProvider(this).get(SignupViewModel::class.java)
    }

    private lateinit var binding: FragmentSignupNicknameBinding

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
            R.layout.fragment_signup_nickname, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = signupViewModel

        mActivity = activity as SignupActivity
    }

    private fun setLiveDataObserver() {
        signupViewModel.signUpNicknameClick.observe(viewLifecycleOwner, Observer {
            signupViewModel.setSignupNickname(et_signup_nickname.text.toString())
            signupViewModel.checkCorrectNickname()
        })

        signupViewModel.signUpNicknameError.observe(viewLifecycleOwner, Observer {
            setErrorSignupNickname()
        })

        signupViewModel.signUpNicknameFinish.observe(viewLifecycleOwner, Observer {
            mActivity.goToMain()
        })
    }

    private fun setErrorSignupNickname() {
        signup_nickname_errorMessage.text = resources.getText(R.string.signup_error_nickname)
        et_signup_nickname.background.setTint(ContextCompat.getColor(mActivity, R.color.colorRed))
    }
}
