/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivitySelectMyTeamBinding
import kr.yapp.teamplay.domain.entity.MyTeam
import kr.yapp.teamplay.presentation.myteam.create.TeamCreateActivity

class MyTeamSelectActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MyTeamSelectActivity"
    }

    private lateinit var binding: ActivitySelectMyTeamBinding

    private val viewModel: MyTeamSelectViewModel by lazy {
        ViewModelProvider(this).get(MyTeamSelectViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setRecyclerView()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_my_team)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setRecyclerView() {
        binding.myTeamList.run {
            layoutManager = LinearLayoutManager(this@MyTeamSelectActivity, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = MyTeamAdapter {
                startActivity(Intent(this@MyTeamSelectActivity, TeamCreateActivity::class.java))
            }.apply {
                updateMyTeam(listOf(MyTeam(), MyTeam(), MyTeam(isCreateCard = true)))
            }
            object:PagerSnapHelper() {
            }.attachToRecyclerView(this)
        }
    }
}

//val category: String = "basketball",
//    val teamName: String = "상암동 농구클럽",
//    val teamLocation: String = "서울시 마포구 우리기술사옥",
//    val since: String = "2020.04.30",
//    val userCount: Int = 30,