package kr.yapp.teamplay.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ItemTeamListBinding
import kr.yapp.teamplay.databinding.ItemTeamListHeaderBinding
import kr.yapp.teamplay.domain.entity.ClubListInfo
import kr.yapp.teamplay.presentation.util.widget.Bindable
import kr.yapp.teamplay.presentation.util.widget.BindableData

/**
 * Created by Lee Oh Hyoung on 2020/05/23.
 */
class TeamListAdapter(
    private val onFilterClick: () -> Unit = {},
    private val onTeamClick: (Int) -> Unit = {}
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        HEADER, LIST
    }

    private val bindableData: MutableList<BindableData> = mutableListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(ViewType.values()[viewType]) {
            ViewType.HEADER -> TeamListHeaderViewHolder(
                DataBindingUtil
                    .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_team_list_header, parent, false)
            )
            ViewType.LIST -> TeamListViewHolder(
                DataBindingUtil
                    .inflate(LayoutInflater.from(parent.context),
                        R.layout.item_team_list, parent, false)
            )
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? Bindable<BindableData>)?.bindTo(bindableData[position])
    }

    override fun getItemCount(): Int = bindableData.count()

    override fun getItemViewType(position: Int): Int = bindableData[position].viewType

    fun update(teamInfo: Pair<List<ClubListInfo>, Int>) {
        bindableData.clear()
        bindableData += TeamListHeaderViewHolder.Item(teamInfo.second, onFilterClick)
        bindableData += teamInfo.first.map { club ->
            TeamListViewHolder.Item(club, onTeamClick) }
        notifyDataSetChanged()
    }

    class TeamListHeaderViewHolder(
        private val binding: ItemTeamListHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root),
        Bindable<TeamListHeaderViewHolder.Item> {

        data class Item(
            val count: Int,
            val onFilterClick: () -> Unit
        ) : BindableData(ViewType.HEADER.ordinal)

        override fun bindTo(item: Item) {
            binding.item = item
        }
    }

    class TeamListViewHolder (
        private val binding: ItemTeamListBinding
    ) : RecyclerView.ViewHolder(binding.root),
        Bindable<TeamListViewHolder.Item> {

        class Item(
            val team: ClubListInfo,
            val onTeamClick: (id: Int) -> Unit
        ) : BindableData(ViewType.LIST.ordinal)

        override fun bindTo(item: Item) {
            binding.item = item
            binding.root.setOnClickListener { item.onTeamClick.invoke(item.team.id) }
        }
    }
}

@BindingAdapter("setTeamList")
fun setTeamList(recyclerView: RecyclerView, teamInfo: Pair<List<ClubListInfo>, Int>?) {
    teamInfo?.let { info ->
        val adapter = recyclerView.adapter as? TeamListAdapter
        adapter?.update(info)
    }
}
