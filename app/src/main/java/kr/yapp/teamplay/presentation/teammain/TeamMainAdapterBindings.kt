package kr.yapp.teamplay.presentation.teammain

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.data.teammain.response.TeamMainFeedItemResponse

@BindingAdapter("bind:item")
fun bindItem(recyclerView: RecyclerView, list: List<TeamMainFeedItemResponse>?) {
    val todoAdapter: TeamMainAdapter = recyclerView.adapter as TeamMainAdapter
    if (!list.isNullOrEmpty()) todoAdapter.updateTeamMain(list)
}