/*
 * Created by Lee Oh Hyoung on 2020/06/13 .. 
 */
package kr.yapp.teamplay.presentation.search.join

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.RvItemTeamJoinQuestionBinding

class TeamJoinQuestionAdapter(
    private val questions: List<String>
) : RecyclerView.Adapter<TeamJoinQuestionAdapter.TeamJoinQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamJoinQuestionViewHolder =
        TeamJoinQuestionViewHolder(
            RvItemTeamJoinQuestionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TeamJoinQuestionViewHolder, position: Int) =
        holder.bindTo(questions[position])

    override fun getItemCount(): Int = questions.size

    inner class TeamJoinQuestionViewHolder(
        private val binding: RvItemTeamJoinQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(question: String) {
            binding.question = question
        }
    }
}