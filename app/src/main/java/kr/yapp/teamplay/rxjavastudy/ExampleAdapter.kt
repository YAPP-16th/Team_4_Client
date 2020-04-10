package kr.yapp.teamplay.rxjavastudy

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.yapp.teamplay.databinding.ItemListBinding
import kr.yapp.teamplay.rxjavastudy.model.Result

class ExampleAdapter(val list : List<Result>, val viewModel: ExampleViewModel) : RecyclerView.Adapter<ViewHolder>() {

    private lateinit var mBinding : ItemListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("MyTag", "createViewHolder")
        return ViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], viewModel)
        Log.d("MyTag", "bindViewHolder")
    }
}

class ViewHolder(private var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item : Result, viewModel: ExampleViewModel) {
        binding.data = item
        binding.viewModel = viewModel
    }
}
