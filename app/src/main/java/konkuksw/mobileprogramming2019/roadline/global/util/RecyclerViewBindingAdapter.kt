package konkuksw.mobileprogramming2019.roadline.global.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListAdapter

object RecyclerViewBindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun RecyclerView.setData(items: MutableList<Travel>?){
        items?.let{
            (adapter as TravelListAdapter).submitList(it) //For ListAdapter
             Log.d("BindingAdapter",(adapter as TravelListAdapter).itemCount.toString())

        }
    }
}