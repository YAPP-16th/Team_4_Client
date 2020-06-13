/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.presentation.search.join

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityTeamJoinBinding
import kr.yapp.teamplay.util.Constant
import kr.yapp.teamplay.util.PreferenceManager
import kr.yapp.teamplay.util.dpToPixel
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class TeamJoinActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_CLUB_ID: String = "club_id"

        fun start(context: Context, clubId: Int) {
            context.startActivity(
                context.intentFor<TeamJoinActivity>(
                    EXTRA_CLUB_ID to clubId
                )
            )
        }
    }

    private lateinit var binding: ActivityTeamJoinBinding
    private val viewModel: TeamJoinViewModel by viewModels()
    private var clubId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        getIntentExtra()
        setListener()
        setLiveDataObserver()
        viewModel.getClubJoinInfo(clubId)
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_join)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun getIntentExtra() {
        clubId = intent.getIntExtra(EXTRA_CLUB_ID, 0)
    }

    private fun setLiveDataObserver() {
        viewModel.uiState.observe(this, Observer { state ->
            when(state) {
                is TeamJoinUiState.Content -> {
                    binding.teamJoinTitle.text = state.teamName
                    binding.teamCreateDate.text = state.createDate
                    binding.teamCreateLocation.text = state.location
                    binding.teamCreateMemberCount.text = "${state.memberCount}명"
                    binding.teamCreateIntroduce.text = state.message
                    binding.teamJoinQuestions.apply {
                        layoutManager = LinearLayoutManager(this@TeamJoinActivity)
                        itemAnimator = DefaultItemAnimator()
                        adapter = TeamJoinQuestionAdapter(Constant.defaultQuestions)
                        addItemDecoration(object: RecyclerView.ItemDecoration() {
                            override fun getItemOffsets(
                                outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State
                            ) {
                                if(getChildAdapterPosition(view) != 0) {
                                    outRect.top = 16.dpToPixel()
                                }
                            }
                        })
                    }
                }
                is TeamJoinUiState.Error -> toast(state.message)
                TeamJoinUiState.JoinSuccess -> {
                    toast(state.message)
                    finish()
                }
            }
        })
    }

    private fun setListener() {
        binding.backButton.setOnClickListener { onBackPressed() }
        binding.requestTeamJoin.setOnClickListener {
            val accessToken: String = PreferenceManager.getTokenKey(applicationContext)
            viewModel.requestClubJoin(accessToken, clubId)
        }
    }
}
