package kr.yapp.teamplay.presentation.match_detail

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.domain.entity.matchlist.MatchInfo
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
        val matchInfo = intent.getSerializableExtra("matchInfo") as MatchInfo?
        viewModel.matchInfo.value = matchInfo
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.matchDetailRegister.setOnClickListener {
            postMatchRequest()
        }
    }

    private fun setLiveDataObserver() {
        viewModel.matchRequestSuccess.observe(this, Observer {
            Toast.makeText(this, "신청을 완료했습니다.", Toast.LENGTH_LONG).show()
            finishDetailActivity()
        })

        viewModel.matchRequestFail.observe(this, Observer {
            Toast.makeText(this, "신청할 수 없습니다. 이미 신청했을 수도 있습니다.", Toast.LENGTH_LONG).show()
        })
    }

    private fun clickBackIcon() {
        binding.matchDetailIcBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun postMatchRequest() {
        val contact = binding.matchDetailAddressRequestInput.text.toString()
        viewModel.postMatchRequest(contact)
    }

    private fun finishDetailActivity() {
        val intent = Intent()
        setResult(2, intent)
        finish()
    }
}