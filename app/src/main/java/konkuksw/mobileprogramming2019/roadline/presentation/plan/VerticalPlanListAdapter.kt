package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.ItemDateButtonBinding
import konkuksw.mobileprogramming2019.roadline.databinding.ItemVerticalPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.plan.VerticalPlanListAdapter.*

class VerticalPlanListAdapter(
    private val viewModel: PlanViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClickListener: OnItemClickListener): ListAdapter<Plan, PlanViewHolder>(
    PlanDiffUtil()
) {
    interface OnItemClickListener {
        fun onItemClick(plan: Plan)
    }

    inner class PlanViewHolder(var binding: ItemVerticalPlanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plan){
            binding.plan = item
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(ItemVerticalPlanBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PlanDiffUtil : DiffUtil.ItemCallback<Plan>() {
    override fun areItemsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Plan, newItem: Plan): Boolean {
        return oldItem == newItem
    }
}