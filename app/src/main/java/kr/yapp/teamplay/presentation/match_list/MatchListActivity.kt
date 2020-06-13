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
import kr.yapp.teamplay.data.SharedPreferenceManager
import kr.yapp.teamplay.databinding.ActivityMatchListBinding
import kr.yapp.teamplay.domain.entity.ConstValue
import kr.yapp.teamplay.domain.entity.matchlist.Search
import kr.yapp.teamplay.presentation.match_detail.MatchDetailActivity

class MatchListActivity : AppCompatActivity() {

    private val viewModel: MatchListViewModel by lazy {
        ViewModelProvider(this).get(MatchListViewModel::class.java)
    }

    private lateinit var binding: ActivityMatchListBinding

    private lateinit var adapter : MatchListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setLiveDataObserver()

        binding.matchListSearch.setOnClickListener {
            startMatchSearchActivity()
        }
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.matchListIcBack.setOnClickListener {
            onBackPressed()
        }

        setRecyclerView()
    }

    private fun setLiveDataObserver() {
        viewModel.itemClick.observe(this, Observer {
            startDetailList()
        })

        viewModel.registerClick.observe(this, Observer {
            startMatchRegister()
        })

        viewModel.matchList.observe(this, Observer {
            if (viewModel.matchList.value?.size != 0) {
                addMatchList()
            }
        })
    }

    private fun addMatchList() {
        adapter.setData(viewModel.matchList.value!!)
    }

    private fun setRecyclerView() {
        viewModel.getListData() // 초기 데이터 설정
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = MatchListAdapter(viewModel)
        match_list_recyclerView.layoutManager = layoutManager
        match_list_recyclerView.adapter = adapter
        match_list_recyclerView.addOnScrollListener(refreshListScroll)
    }

    private fun startDetailList() {
        val intent = Intent(this, MatchDetailActivity::class.java)
            .putExtra("matchInfo",viewModel.matchInfo.value)
        startActivity(intent)
    }

    private fun startMatchSearchActivity() {
        val intent = Intent(this, MatchSearchActivity::class.java)
        startActivityForResult(intent, 1)
    }

    private fun startMatchRegister() {
        Toast.makeText(this, "매칭 등록 화면으로 이동합니다.", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==1 && resultCode==2) {
            val search : Search = data?.getSerializableExtra("search") as Search
            viewModel.setSearch(search)
            viewModel.getSearchListData()
        }
    }

    val refreshListScroll : RecyclerView.OnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItem : Int = linearLayoutManager.itemCount
            var lastItem : Int = linearLayoutManager.findLastCompletelyVisibleItemPosition()

            if (dy > 0 && lastItem >= totalItem-1) {
                viewModel.getListData()
            }
            super.onScrolled(recyclerView, dx, dy)
        }
    }
}
