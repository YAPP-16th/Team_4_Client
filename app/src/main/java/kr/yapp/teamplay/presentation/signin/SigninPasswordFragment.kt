package kr.yapp.teamplay.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.FragmentSigninPasswordBinding

class SigninPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSigninPasswordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_signin_password, container, false)

        return binding.root
    }
}
