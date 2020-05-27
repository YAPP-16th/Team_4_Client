/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivitySelectMyTeamBinding
import kr.yapp.teamplay.domain.entity.MyTeam
import kr.yapp.teamplay.presentation.myteam.create.TeamCreateActivity
import kr.yapp.teamplay.presentation.search.TeamSearchActivity
import kr.yapp.teamplay.presentation.teammain.TeamMainActivity

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
        setLiveDataObserver()
        transStatusWhiteTextBar()
        setRecyclerView()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_my_team)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setLiveDataObserver() {
        viewModel.searchClick.observe(this, Observer {
            TeamSearchActivity.start(this)
        })
    }

    private fun setRecyclerView() {
        with(binding.myTeamList) {
            layoutManager = LinearLayoutManager(this@MyTeamSelectActivity, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = MyTeamAdapter(
                onCardClick = { TeamCreateActivity.start(this@MyTeamSelectActivity) },
                onCardClickToGoTeamMain = { TeamMainActivity.start(this@MyTeamSelectActivity) }
            ).apply {
                updateMyTeam(listOf(MyTeam(), MyTeam(), MyTeam(isCreateCard = true)))
            }
            object : PagerSnapHelper() {
            }.attachToRecyclerView(this)
        }
    }


    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}
