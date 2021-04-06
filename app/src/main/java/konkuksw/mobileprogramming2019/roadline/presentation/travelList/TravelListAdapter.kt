package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.databinding.ItemTravelBinding

data class Travel(
    val id:Int
)
class TravelListAdapter():ListAdapter<Travel,TravelListAdapter.TravelViewHolder>(
    TravelDiffUtil()
) {

    class TravelViewHolder(private var binding:ItemTravelBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item:Travel){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        return TravelViewHolder(ItemTravelBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        fun bind(item:Travel){

        }
    }
}

class TravelDiffUtil : DiffUtil.ItemCallback<Travel>() {
    override fun areItemsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Travel, newItem: Travel): Boolean {
        return oldItem == newItem
    }
}