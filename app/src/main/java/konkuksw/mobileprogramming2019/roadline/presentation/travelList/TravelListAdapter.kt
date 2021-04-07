package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.databinding.ItemTravelBinding
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListAdapter.*

class TravelListAdapter(private val onItemClickListener: OnItemClickListener):ListAdapter<Travel, TravelViewHolder>(
    TravelDiffUtil()
) {
    interface OnItemClickListener{
        fun onItemClick(travel: Travel)
        fun onEditClick(travel: Travel)
        fun onDeleteClick(travel: Travel)
    }

    inner class TravelViewHolder(var binding:ItemTravelBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Travel){
            binding.item = item
            binding.onItemClickListener = onItemClickListener
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        return TravelViewHolder(ItemTravelBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TravelDiffUtil : DiffUtil.ItemCallback<Travel>() {
    override fun areItemsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem == newItem
    }
}