package kr.yapp.teamplay.presentation.match_detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.domain.entity.MatchInfo
import kr.yapp.teamplay.databinding.ActivityMatchDetailBinding

class MatchDetailActivity : AppCompatActivity() {

    private val viewModel: MatchDetailViewModel by lazy {
        ViewModelProvider(this).get(MatchDetailViewModel::class.java)
    }

    private lateinit var binding: ActivityMatchDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setLiveDataObserver()
        clickBackIcon()
        setMatchInfo()
    }

    private fun setMatchInfo() {
        val intent = getIntent()
        val matchInfo = intent.getSerializableExtra("matchInfo") as MatchInfo?
        viewModel.matchInfo.value = matchInfo
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setLiveDataObserver() {

    }

    private fun clickBackIcon() {
        binding.matchDetailIcBack.setOnClickListener {
            onBackPressed()
        }
    }
}