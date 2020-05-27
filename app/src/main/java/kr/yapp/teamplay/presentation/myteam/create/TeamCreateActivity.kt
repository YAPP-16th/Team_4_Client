/*
 * Created by Lee Oh Hyoung on 2020/05/09 .. 
 */
package kr.yapp.teamplay.presentation.myteam.create

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityTeamCreateBinding
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop

class TeamCreateActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(
                context.intentFor<TeamCreateActivity>().singleTop()
            )
        }
    }

    private lateinit var binding: ActivityTeamCreateBinding

    private val viewModel: TeamCreateViewModel by lazy {
        ViewModelProvider(this).get(TeamCreateViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        transStatusWhiteTextBar()
        setListener()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_team_create)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setListener() {
        binding.teamCreateBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.questionCreateButton.setOnClickListener {
            addQuestionEditText()
        }
    }

    private fun addQuestionEditText() {
        LayoutInflater
            .from(this)
            .inflate(R.layout.item_team_create_question, null).let { view ->
                ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = 24
                }.let { params->
                    view.layoutParams = params
                }.also {
                    binding.teamCreateQuestionLayout.addView(view)
                }
            }
    }

}