package kr.yapp.teamplay.presentation.match_list

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ActivityMatchSearchBinding
import kr.yapp.teamplay.domain.entity.matchlist.MatchStyle
import kr.yapp.teamplay.domain.entity.matchlist.Search
import org.jetbrains.anko.backgroundColor
import java.text.SimpleDateFormat
import java.util.*

class MatchSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMatchSearchBinding
    private lateinit var search : Search
    private lateinit var minDate : Calendar
    private var buttonState = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDataBinding()
        setViewListener()
        setInitView()
        transStatusWhiteTextBar()
    }

    private fun setInitView() {
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일")
        val currentTime = dateFormat.format(System.currentTimeMillis()).toString()
        binding.searchStartDate.hint = currentTime
        binding.searchEndDate.hint = currentTime
    }

    private fun setDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_match_search)
        binding.lifecycleOwner = this
        search = Search()
    }

    private fun setViewListener() {
        val startDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            minDate = Calendar.getInstance()
            minDate.set(year, month, dayOfMonth)

            val calendar = Calendar.getInstance()
            calendar.set(year,month,dayOfMonth)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            search.startTime = formatter.format(calendar.time)

            binding.searchStartDate.text = search.startTime
            binding.searchStartDate.background = resources.getDrawable(R.drawable.item_search_active_label)
        }

        val endDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year,month,dayOfMonth)
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            search.endTime = formatter.format(calendar.time)

            binding.searchEndDate.text = search.endTime
            binding.searchEndDate.background = resources.getDrawable(R.drawable.item_search_active_label)
        }

        binding.searchStartDate.setOnClickListener {
            getDatePickerDialog(startDateSetListener).show()
        }

        binding.searchEndDate.setOnClickListener {
            val datePickerDialog = getDatePickerDialog(endDateSetListener)
            datePickerDialog.datePicker.minDate = minDate.timeInMillis
            datePickerDialog.show()
        }

        binding.searchThreeOnThree.setOnClickListener {
            setButtonColor()
            binding.searchThreeOnThree.backgroundColor = resources.getColor(R.color.colorBasketBallRed2)
            binding.searchThreeOnThree.setTextColor(resources.getColor(R.color.colorWhite))
            buttonState = 1
        }

        binding.searchFiveOnFiveHalf.setOnClickListener {
            setButtonColor()
            binding.searchFiveOnFiveHalf.backgroundColor = resources.getColor(R.color.colorBasketBallRed2)
            binding.searchFiveOnFiveHalf.setTextColor(resources.getColor(R.color.colorWhite))
            buttonState = 2
        }

        binding.searchFiveOnFiveFull.setOnClickListener {
            setButtonColor()
            binding.searchFiveOnFiveFull.backgroundColor = resources.getColor(R.color.colorBasketBallRed2)
            binding.searchFiveOnFiveFull.setTextColor(resources.getColor(R.color.colorWhite))
            buttonState = 3
        }

        binding.searchEtc.setOnClickListener {
            setButtonColor()
            binding.searchEtc.backgroundColor = resources.getColor(R.color.colorBasketBallRed2)
            binding.searchEtc.setTextColor(resources.getColor(R.color.colorWhite))
            buttonState = 4
        }

        binding.searchSubmit.setOnClickListener {
            search.location = binding.searchEtLocation.text.toString()
            search.matchRule = when(buttonState) {
                1 -> MatchStyle.THREE_HALF_COURT.toString()
                2 -> MatchStyle.FIVE_HALF_COURT.toString()
                3 -> MatchStyle.FIVE_FULL_COURT.toString()
                else -> ""
            }
            finishSearchActivity()
        }

        binding.msIcBack.setOnClickListener {
            finishSearchActivity()
        }
    }

    private fun finishSearchActivity() {
        val intent = Intent()
        intent.putExtra("search", search)
        setResult(1, intent)
        finish()
    }

    private fun setButtonColor() {
        binding.searchThreeOnThree.backgroundColor = resources.getColor(R.color.colorBackgroundGray)
        binding.searchThreeOnThree.setTextColor(resources.getColor(R.color.colorWireFrameBackground))
        binding.searchFiveOnFiveHalf.backgroundColor = resources.getColor(R.color.colorBackgroundGray)
        binding.searchFiveOnFiveHalf.setTextColor(resources.getColor(R.color.colorWireFrameBackground))
        binding.searchFiveOnFiveFull.backgroundColor = resources.getColor(R.color.colorBackgroundGray)
        binding.searchFiveOnFiveFull.setTextColor(resources.getColor(R.color.colorWireFrameBackground))
        binding.searchEtc.backgroundColor = resources.getColor(R.color.colorBackgroundGray)
        binding.searchEtc.setTextColor(resources.getColor(R.color.colorWireFrameBackground))
    }

    private fun getDatePickerDialog(dateSetListener: DatePickerDialog.OnDateSetListener): DatePickerDialog {
        val cal = Calendar.getInstance()
        cal.timeInMillis
        return DatePickerDialog(this, dateSetListener
            , cal.get(Calendar.YEAR)
            , cal.get(Calendar.MONTH)
            , cal.get(Calendar.DAY_OF_WEEK))
    }

    private fun transStatusWhiteTextBar() {
        window.run {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = resources.getColor(R.color.colorBasketBallRed2)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishSearchActivity()
    }
}
