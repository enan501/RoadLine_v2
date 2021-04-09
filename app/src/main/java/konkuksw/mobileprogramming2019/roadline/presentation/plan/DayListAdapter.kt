package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.databinding.ItemDateButtonBinding
import konkuksw.mobileprogramming2019.roadline.presentation.plan.DayListAdapter.*

class DayListAdapter(private val onItemClickListener: OnItemClickListener): ListAdapter<Day, DayViewHolder>(
    DayDiffUtil()
) {
    private var selectedPos = -1

    interface OnItemClickListener {
        fun onItemClick(day: Day?)
    }

    inner class DayViewHolder(var binding: ItemDateButtonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Day){
            binding.item = item
            binding.selected = selectedPos == adapterPosition
            binding.onItemClickListener = onItemClickListener
            binding.dayNum = adapterPosition + 1
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