package kr.yapp.teamplay.presentation.match_list

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_match_list.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityMatchListBinding
import kr.yapp.teamplay.domain.entity.MatchList
import kr.yapp.teamplay.presentation.match_detail.MatchDetailActivity

class MatchListActivity : AppCompatActivity() {

    private val mViewModel: MatchListViewModel by lazy {
        ViewModelProvider(this).get(MatchListViewModel::class.java)
    }

    private lateinit var binding: ActivityMatchListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setLiveDataObserver()
        initRecyclerView()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_list)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
    }

    private fun setLiveDataObserver() {
        mViewModel.itemClick.observe(this, Observer {
            goToDetailList()
        })

        mViewModel.registerClick.observe(this, Observer {
            goToMatchRegister()
        })
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = MatchListAdapter(this)
        adapter.setData(listOf(MatchList(), MatchList()))
        match_list_recyclerView.layoutManager = layoutManager
        match_list_recyclerView.adapter = adapter
    }

    private fun goToDetailList() {
        Toast.makeText(this, "디테일 화면으로 이동합니다.", Toast.LENGTH_LONG).show()
        val intent = Intent(this, MatchDetailActivity::class.java)
        startActivity(intent)
    }

    private fun goToMatchRegister() {
        Toast.makeText(this, "매칭 등록 화면으로 이동합니다.", Toast.LENGTH_LONG).show()
    }
}