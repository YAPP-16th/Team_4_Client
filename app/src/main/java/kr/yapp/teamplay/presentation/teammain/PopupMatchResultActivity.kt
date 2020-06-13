package kr.yapp.teamplay.presentation.teammain

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityPopupMatchResultBinding

class PopupMatchResultActivity : AppCompatActivity() {

    private var teamName: String? = ""
    private var isWin: Boolean = false
    private lateinit var binding: ActivityPopupMatchResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isWin = intent.getBooleanExtra("isWin", false)
        teamName = intent.getStringExtra("teamName")
        setDataBinding()
        setUI()
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_popup_match_result)
    }

    private fun setUI() {
        binding.popupMatchResultNameTv.setText(teamName)
        if (isWin) {
            binding.popupMatchResultResultIvWin.visibility = View.VISIBLE
            binding.itemResultTypeMatchResultIvLose.visibility = View.INVISIBLE
            binding.popupMatchResultDescription.setText(String.format("%s 과의 경기에서 승리하셨습니다.", teamName))
        } else {
            binding.itemResultTypeMatchResultIvLose.visibility = View.VISIBLE
            binding.popupMatchResultResultIvWin.visibility = View.INVISIBLE
            binding.popupMatchResultDescription.setText(String.format("%s 과의 경기에서 패배하셨습니다.", teamName))
        }
    }
}