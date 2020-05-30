package kr.yapp.teamplay.presentation.search

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityTeamSearchBinding
import kr.yapp.teamplay.presentation.filter.TeamSearchFilterActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
class TeamSearchActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(
                context.intentFor<TeamSearchActivity>().singleTop()
            )
        }
    }

    private lateinit var binding: ActivityTeamSearchBinding

    private val viewModel: TeamSearchViewModel by lazy {
        ViewModelProvider(this).get(TeamSearchViewModel::class.java)
    }

    private val teamListAdapter: TeamListAdapter by lazy {
        TeamListAdapter(
            onFilterClick = { TeamSearchFilterActivity.start(this) },
            onTeamClick = { /* Move Team Join Activity */ }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setToolbar()
        setRecyclerView()
        viewModel.getAllClub()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_search)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbar as? Toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.toolbar.findViewById<TextView>(R.id.toolbar_title).text = "팀 찾기"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setRecyclerView() {
        with(binding.teamList) {
            layoutManager = LinearLayoutManager(this@TeamSearchActivity)
            itemAnimator = DefaultItemAnimator()
            adapter = teamListAdapter
        }
    }
}
