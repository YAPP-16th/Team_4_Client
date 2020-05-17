package kr.yapp.teamplay.presentation.match_list

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.R
import kr.yapp.teamplay.databinding.ItemMatchListBinding
import kr.yapp.teamplay.domain.entity.MatchList

class MatchListAdapter(viewModelStoreOwner: ViewModelStoreOwner) : RecyclerView.Adapter<MatchListAdapter.MatchListViewHolder>() {
    private val matchList = ArrayList<MatchList>()
    private val mViewModel: MatchListViewModel by lazy {
        ViewModelProvider(viewModelStoreOwner).get(MatchListViewModel::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        val binding : ItemMatchListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_match_list, parent, false)
        return MatchListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matchList.count()
    }

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        holder.bind(matchList[position])
    }

    fun setData(matchList : List<MatchList>) {
        this.matchList.addAll(matchList)
        notifyDataSetChanged()
    }

    inner class MatchListViewHolder(
        private val binding : ItemMatchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(matchlist : MatchList) {
            if (matchlist != null) {
                binding.matchList = matchlist
                binding.mlLayout.setOnClickListener {
                    mViewModel.clickMatchListItem()
                }
            } else {
                Log.d("MatchList", "matchlist is null!")
            }
        }

    }
}