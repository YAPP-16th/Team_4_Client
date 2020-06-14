/*
 * Created by Lee Oh Hyoung on 2020/06/14 .. 
 */
package kr.yapp.teamplay.presentation.match_result

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.yapp.teamplay.R

import kr.yapp.teamplay.databinding.ActivityMatchDetailedResultBinding

class MatchDetailedResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMatchDetailedResultBinding
    private val viewModel: MatchDetailedResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setLiveDataObserver()
        setRecyclerView()

        // TODO ViewModel로 부터 경기 상세 결과를 가져오는 함수 호출
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_detailed_result)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setLiveDataObserver() {
        // TODO ViewModel LiveData Observe
    }

    private fun setRecyclerView() {
        // TODO 결기 상세 결과, 개인 기록 RecyclerView 초기화
    }

}
