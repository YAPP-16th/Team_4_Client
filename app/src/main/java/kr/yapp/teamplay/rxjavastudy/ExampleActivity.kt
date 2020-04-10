package kr.yapp.teamplay.rxjavastudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_example.*
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityExampleBinding

class ExampleActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityExampleBinding
    private lateinit var mViewModel : ExampleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_example)
        mViewModel = ExampleViewModel()
        mBinding.viewModel = mViewModel
        mViewModel.mapData()
        observeViewModel()
    }

    fun observeViewModel() {
        mViewModel.initListCallback.observe(this,
            Observer {
                initRecyclerView()
            })
    }

    fun initRecyclerView() {
        val adapter = ExampleAdapter(mViewModel.list,mViewModel)
        recyclerView.adapter = adapter
        val mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = mLayoutManager
    }
}
