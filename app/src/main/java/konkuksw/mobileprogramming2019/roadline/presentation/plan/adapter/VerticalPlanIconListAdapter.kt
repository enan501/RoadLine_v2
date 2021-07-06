package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.presentation.plan.data.VerticalPlanIcon
import konkuksw.mobileprogramming2019.roadline.databinding.ItemVerticalPlanIconBinding

class VerticalPlanIconListAdapter(): ListAdapter<VerticalPlanIcon, VerticalPlanIconListAdapter.VerticalPlanIconViewHolder>(
    PositionDiffUtil()
) {

    inner class VerticalPlanIconViewHolder(var binding: ItemVerticalPlanIconBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VerticalPlanIcon){
            binding.isFirst = item.isFirst
            binding.isLast = item.isLast
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalPlanIconViewHolder {
        return VerticalPlanIconViewHolder(ItemVerticalPlanIconBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: VerticalPlanIconViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PositionDiffUtil : DiffUtil.ItemCallback<VerticalPlanIcon>() {
    override fun areItemsTheSame(oldItem: VerticalPlanIcon, newItem: VerticalPlanIcon): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: VerticalPlanIcon, newItem: VerticalPlanIcon): Boolean {
        return oldItem == newItem
    }

}