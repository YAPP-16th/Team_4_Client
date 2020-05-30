/*
 * Created by Lee Oh Hyoung on 2020/05/30 .. 
 */
package kr.yapp.teamplay.presentation.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.RvItemCharacterBinding
import kr.yapp.teamplay.domain.entity.TeamCharacter

class TeamCharacterAdapter : RecyclerView.Adapter<TeamCharacterAdapter.TeamCharacterViewHolder>() {

    private val characters: ArrayList<TeamCharacter> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamCharacterViewHolder =
        TeamCharacterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rv_item_character,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TeamCharacterViewHolder, position: Int) =
        holder.bindTo(characters[position])

    override fun getItemCount(): Int = characters.size

    fun update(list: List<TeamCharacter>) {
        characters.clear()
        characters.addAll(list)
        notifyDataSetChanged()
    }

    inner class TeamCharacterViewHolder(
        private val binding: RvItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(character: TeamCharacter) {
            binding.character = character
        }
    }
}

@BindingAdapter("setCharacter")
fun RecyclerView.setCharacter(list: List<TeamCharacter>?) =
    list?.let {
        val adapter = adapter as? TeamCharacterAdapter
        adapter?.update(it)
    }
