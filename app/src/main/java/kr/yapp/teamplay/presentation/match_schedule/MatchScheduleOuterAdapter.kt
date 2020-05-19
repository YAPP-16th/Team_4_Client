package kr.yapp.teamplay.presentation.match_schedule

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.ItemMatchScheduleSubtitleBinding

class MatchScheduleOuterAdapter : RecyclerView.Adapter<MatchScheduleOuterAdapter.MatchScheduleSubTitleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchScheduleSubTitleViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MatchScheduleSubTitleViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class MatchScheduleSubTitleViewHolder(
        private val binding : ItemMatchScheduleSubtitleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}