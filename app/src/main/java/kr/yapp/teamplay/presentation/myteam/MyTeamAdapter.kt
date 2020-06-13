/*
 * Created by Lee Oh Hyoung on 2020/04/30 .. 
 */
package kr.yapp.teamplay.presentation.myteam

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.RvItemMyTeamBinding
import kr.yapp.teamplay.databinding.RvItemTeamCreateBinding
import kr.yapp.teamplay.domain.entity.MyTeam
import kr.yapp.teamplay.util.dpToPixel
import org.jetbrains.anko.windowManager

class MyTeamAdapter(
    private val onCardClick: () -> Unit = {},
    private val onCardClickToGoTeamMain: (id: Int, position : Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        TEAM, CREATE
    }

    private val teams: ArrayList<MyTeam> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ViewType.TEAM .ordinal -> {
                MyTeamViewHolder(
                    RvItemMyTeamBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            ViewType.CREATE.ordinal -> {
                TeamCreateViewHolder(
                    RvItemTeamCreateBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw IllegalArgumentException("Un Support ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            ViewType.TEAM.ordinal -> {
                (holder as MyTeamViewHolder).bindTo(teams[position], teams[position].id.toInt())
            }
            ViewType.CREATE.ordinal -> {
                (holder as TeamCreateViewHolder).bindTo()
            }
        }
    }

    override fun getItemCount(): Int = teams.count()

    override fun getItemViewType(position: Int): Int {
        return if(teams[position].isCreateCard) ViewType.CREATE.ordinal else ViewType.TEAM.ordinal
    }

    fun updateMyTeam(list: List<MyTeam>) {
        teams.clear()
        teams.addAll(list)
        notifyDataSetChanged()
    }

    inner class MyTeamViewHolder(
        private val binding: RvItemMyTeamBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val displayMetrics = DisplayMetrics()

        fun bindTo(myTeam: MyTeam, id: Int) {
            binding.myTeam = myTeam
            binding.root.setOnClickListener { onCardClickToGoTeamMain(id, adapterPosition) }
            itemView.context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            itemView.layoutParams.width = (displayMetrics.widthPixels.toDouble() - 64.dpToPixel()).toInt()
        }
    }

    inner class TeamCreateViewHolder(
        private val binding: RvItemTeamCreateBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val displayMetrics = DisplayMetrics()

        fun bindTo() {
            binding.root.setOnClickListener { onCardClick() }
            itemView.context.windowManager.defaultDisplay.getMetrics(displayMetrics)
            itemView.layoutParams.width = (displayMetrics.widthPixels.toDouble() - 64.dpToPixel()).toInt()
        }
    }
}
