package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.ItemDateButtonBinding
import konkuksw.mobileprogramming2019.roadline.databinding.ItemVerticalPlanBinding
import konkuksw.mobileprogramming2019.roadline.global.extension.totalMinToString
import konkuksw.mobileprogramming2019.roadline.presentation.plan.VerticalPlanListAdapter.*

class VerticalPlanListAdapter(
    private val viewModel: PlanViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClickListener: OnItemClickListener): ListAdapter<Plan, PlanViewHolder>(
    PlanDiffUtil()
), ItemTouchHelperListener {
    interface OnItemClickListener {
        fun onItemClick(plan: Plan)
        fun onItemLongClick(plan: Plan): Boolean
        fun onItemDrag(plan: Plan, viewHolder: PlanViewHolder)
        fun onItemDelete(plan: Plan)
    }

    inner class PlanViewHolder(var binding: ItemVerticalPlanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plan){
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
            binding.plan = item
            binding.time = totalMinToString(item.time)
            binding.listener = onItemClickListener
            binding.position = layoutPosition
            binding.itemCount = itemCount
            binding.viewHolder = this
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        return PlanViewHolder(ItemVerticalPlanBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onItemMove(fromPos: Int, toPos: Int): Boolean {
        val originList = currentList.toMutableList()
        val item = originList.removeAt(fromPos)
        originList.add(toPos, item)
        submitList(originList.toList())
        return true
    }


    fun updatePosition(startPos: Int, endPos: Int) {
        viewModel.updatePosition(currentList, startPos, endPos)
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