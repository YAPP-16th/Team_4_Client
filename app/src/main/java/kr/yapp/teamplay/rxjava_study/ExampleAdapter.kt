package kr.yapp.teamplay.rxjava_study

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.ItemListBinding
import kr.yapp.teamplay.rxjava_study.model.Result

class ExampleAdapter(val list : List<Result>, val viewModel: ExampleViewModel) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var mBinding : ItemListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], viewModel)
    }
}

class ViewHolder(private var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Result, viewModel: ExampleViewModel) {
        binding.data = item
        binding.viewModel = viewModel
    }
}