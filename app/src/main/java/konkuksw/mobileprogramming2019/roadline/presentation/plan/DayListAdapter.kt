package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.databinding.ItemDateButtonBinding
import konkuksw.mobileprogramming2019.roadline.presentation.plan.DayListAdapter.*

class DayListAdapter(
    private val viewModel: PlanViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onItemClickListener: OnItemClickListener): ListAdapter<Day, DayViewHolder>(
    DayDiffUtil()
) {
    interface OnItemClickListener {
        fun onItemClick(dayNum: Int?)
    }

    inner class DayViewHolder(var binding: ItemDateButtonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Day){

            binding.item = item
            binding.onItemClickListener = onItemClickListener
            binding.dayNum = adapterPosition + 1
            binding.lifecycleOwner = lifecycleOwner
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(ItemDateButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DayDiffUtil : DiffUtil.ItemCallback<Day>() {
    override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
        return oldItem == newItem
    }
}