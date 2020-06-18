/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import android.content.Intent
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
import kr.yapp.teamplay.TeamPlayApplication
import kr.yapp.teamplay.databinding.ActivitySelectMyTeamBinding
import kr.yapp.teamplay.domain.entity.MyTeam
import kr.yapp.teamplay.domain.entity.UserRole
import kr.yapp.teamplay.presentation.myteam.create.TeamCreateActivity
import kr.yapp.teamplay.presentation.search.TeamSearchActivity
import kr.yapp.teamplay.presentation.signin.SigninActivity
import kr.yapp.teamplay.presentation.teammain.TeamMainActivity
import kr.yapp.teamplay.util.PreferenceManager

class MyTeamSelectActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MyTeamSelectActivity"
    }

    private lateinit var binding: ActivitySelectMyTeamBinding

    private var myTeamList = mutableListOf<MyTeam>()

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

    override fun onResume() {
        super.onResume()
        viewModel.getMyTeams()
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
        viewModel.clubInfoList.observe(this, Observer {item ->
            val myTeams = item.clubsInfo
                .map { clubInfo ->
                    MyTeam(
                        clubInfo.clubId.toString(),
                        "BASKETBALL",
                        clubInfo.name,
                        clubInfo.location,
                        clubInfo.createDate,
                        clubInfo.memberCount.toInt(),
                        false
                    )
                }
            val list = mutableListOf<MyTeam>()
            list.addAll(myTeams)
            list.add(MyTeam("0","CREATE", "팀 생성 페이지 이동", "클릭하면 팀 생성 페이지로 넘어갑니다.", "", 0, true))

            with(binding.myTeamList) {
                (adapter as MyTeamAdapter).updateMyTeam(list)
                adapter?.notifyDataSetChanged()
            }
        })
        viewModel.logoutClick.observe(this, Observer {
            PreferenceManager.setTokenKey(TeamPlayApplication.appContext, "")
            PreferenceManager.setRefreshTokenKey(TeamPlayApplication.appContext, "")
            SigninActivity.start(this)
        })
    }

    private fun setRecyclerView() {
        with(binding.myTeamList) {
            layoutManager = LinearLayoutManager(this@MyTeamSelectActivity, RecyclerView.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
            adapter = MyTeamAdapter(
                onCardClick = { TeamCreateActivity.start(this@MyTeamSelectActivity) },
                onCardClickToGoTeamMain = {id, position ->
                    val intent = Intent(this@MyTeamSelectActivity, TeamMainActivity::class.java)
                    intent.putExtra("isAdmin", viewModel.clubInfoList.value!!.clubsInfo[position].userRole == UserRole.ADMIN)
                    intent.putExtra("id" ,id)
                    PreferenceManager.setSelectedTeamId(TeamPlayApplication.appContext, id)
                    startActivity(intent)
                }
            ).apply {
                updateMyTeam(arrayListOf(MyTeam()))
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
