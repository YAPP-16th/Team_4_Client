package kr.yapp.teamplay.presentation.match_schedule

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.ItemMatchScheduleHostGuestBinding
import kr.yapp.teamplay.databinding.ItemMatchScheduleReservedBinding
import kr.yapp.teamplay.databinding.ItemMatchScheduleSubtitleBinding

class MatchScheduleInnerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class MatchScheduleReservedViewHolder(
        private val binding : ItemMatchScheduleReservedBinding
    ) {

    }

    inner class MatchScheduleHostViewHolder(
        private val binding : ItemMatchScheduleHostGuestBinding
    ) {

    }

    inner class MatchScheduleGuestViewHolder(
        private val binding : ItemMatchScheduleHostGuestBinding
    ) {

    }
}