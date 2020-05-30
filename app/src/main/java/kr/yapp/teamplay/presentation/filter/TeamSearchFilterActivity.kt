/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.presentation.filter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityFilerTeamSearchBinding
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class TeamSearchFilterActivity : AppCompatActivity() {

    companion object {
        private const val SPAN_COUNT: Int = 5

        fun start(context: Context){
            context.startActivity(
                context.intentFor<TeamSearchFilterActivity>().singleTop()
            )
        }
    }

    private lateinit var binding: ActivityFilerTeamSearchBinding

    private val viewModel: TeamSearchFilterViewModel by lazy {
        ViewModelProvider(this).get(TeamSearchFilterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setRecyclerView()
        viewModel.getTeamCharacters()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filer_team_search)
        binding.lifecycleOwner = this
    }

    private fun setRecyclerView() {
        with(binding.teamCharacterRecyclerView) {
            layoutManager = GridLayoutManager(
                this@TeamSearchFilterActivity,
                SPAN_COUNT
            )
            itemAnimator = DefaultItemAnimator()
            adapter = TeamCharacterAdapter()
        }
    }
}
