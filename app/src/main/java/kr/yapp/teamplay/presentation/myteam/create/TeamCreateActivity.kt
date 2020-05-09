/*
 * Created by Lee Oh Hyoung on 2020/05/09 .. 
 */
package kr.yapp.teamplay.presentation.myteam.create

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityTeamCreateBinding

class TeamCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamCreateBinding

    private val viewModel: TeamCreateViewModel by lazy {
        ViewModelProvider(this).get(TeamCreateViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        transStatusWhiteTextBar()
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

}