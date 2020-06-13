package kr.yapp.teamplay.presentation.teammain

import android.content.Intent
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityTeamMainBinding
import kr.yapp.teamplay.presentation.editpost.EditPostActivity
import kr.yapp.teamplay.presentation.match_list.MatchListActivity
import kr.yapp.teamplay.presentation.match_schedule.MatchScheduleActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class TeamMainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(
                context.intentFor<TeamMainActivity>().singleTop()
            )
        }
    }

    private var isAdmin: Boolean = false
    private lateinit var binding: ActivityTeamMainBinding

    private val viewModel: TeamMainViewModel by lazy {
        ViewModelProvider(this).get(TeamMainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transStatusWhiteTextBar()
        setDataBinding()
        setLiveDataObserver()
        setRecyclerView()
        val position = intent.getIntExtra("id", -1)
        isAdmin = intent.getBooleanExtra("isAdmin", false)
        viewModel.isAdmin.value = isAdmin
        Log.i("TTT", "isADmin : " + isAdmin)
        getTeamMainItem(position)
    }

    private fun setRecyclerView() {
        binding.teamMainRecyclerView.run {
            layoutManager = LinearLayoutManager(this@TeamMainActivity, RecyclerView.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(
                DividerItemDecoration(
                    this@TeamMainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )
            adapter = TeamMainAdapter(
                mutableListOf(),
                onClickNotice = {
                    startActivity(Intent(this@TeamMainActivity, EditPostActivity::class.java))
                }, onClickResult = { isWin, teamName ->
                    val intent = Intent(this@TeamMainActivity, PopupMatchResultActivity::class.java)
                    intent.putExtra("isWin", isWin)
                    intent.putExtra("teamName", teamName)
                    startActivity(intent)
                }
            )
        }
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setLiveDataObserver() {
        viewModel.showShimmer.observe(this, Observer { start ->
            if (start) binding.teamMainShimmerViewContainer.startShimmer()
            else binding.teamMainShimmerViewContainer.stopShimmer()
        })
        viewModel.matchScheduleClick.observe(this, Observer {
            startActivity(Intent(this@TeamMainActivity, MatchScheduleActivity::class.java))
        })
        viewModel.matchListClick.observe(this, Observer {
            startActivity(Intent(this@TeamMainActivity, MatchListActivity::class.java))
        })
    }

    private fun getTeamMainItem(position: Int) {
        viewModel.fetchTeamMainItem(position) { message ->
            Toast.makeText(this@TeamMainActivity, message, Toast.LENGTH_LONG).show()
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