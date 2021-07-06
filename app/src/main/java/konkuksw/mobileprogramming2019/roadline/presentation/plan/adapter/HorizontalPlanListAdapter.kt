package konkuksw.mobileprogramming2019.roadline.presentation.plan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.ItemHorizontalPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.plan.PlanDiffUtil
import konkuksw.mobileprogramming2019.roadline.presentation.plan.PlanViewModel
import konkuksw.mobileprogramming2019.roadline.presentation.plan.adapter.HorizontalPlanListAdapter.*

class HorizontalPlanListAdapter(
    private val viewModel: PlanViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClickListener: OnItemClickListener): ListAdapter<Plan, PlanViewHolder>(
    PlanDiffUtil()
) {
    interface OnItemClickListener {
        fun onItemClick(plan: Plan)
    }

    inner class PlanViewHolder(var binding: ItemHorizontalPlanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plan){
            binding.lifecycleOwner = lifecycleOwner
            binding.plan = item
            binding.listener = onItemClickListener
            binding.position = convertPos(layoutPosition)
            binding.itemCount = getOriginItemCount()
            binding.isEmptyView = false
        }
        fun createEmptyView(){
            binding.isEmptyView = true
        }
    }

    fun getOriginItemCount(): Int {
        return super.getItemCount()
    }
    override fun getItemCount(): Int {
        val count = super.getItemCount()
        if (count % 10 in 6..9) {
            return (count / 10 + 1) * 10
        } else {
            return count
        }
    }


    fun convertPos(position: Int): Int {
        when (position % 10) {
            5 -> return position + 4
            6 -> return position + 2
            8 -> return position - 2
            9 -> return position - 4
            else -> return position
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(ItemHorizontalPlanBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val convertedPosition = convertPos(position)
        if(convertedPosition < getOriginItemCount())
            holder.bind(getItem(convertedPosition))
        else
            holder.createEmptyView()
    }
}
