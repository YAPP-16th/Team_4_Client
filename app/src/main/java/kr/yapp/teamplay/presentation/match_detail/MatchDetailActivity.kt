package kr.yapp.teamplay.presentation.match_detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityMatchDetailBinding
import kr.yapp.teamplay.domain.entity.DetailMatch

class MatchDetailActivity : AppCompatActivity() {

    private val mViewModel: MatchDetailViewModel by lazy {
        ViewModelProvider(this).get(MatchDetailViewModel::class.java)
    }

    private lateinit var binding: ActivityMatchDetailBinding

    private lateinit var mDetailMatch : DetailMatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mDetailMatch = DetailMatch()
        setDataBinding()
        setLiveDataObserver()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
        binding.detailMatch = mDetailMatch
    }

    private fun setLiveDataObserver() {

    }
}