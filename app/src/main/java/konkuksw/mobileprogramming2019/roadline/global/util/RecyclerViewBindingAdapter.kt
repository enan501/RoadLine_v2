package konkuksw.mobileprogramming2019.roadline.global.util

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.presentation.plan.DayListAdapter
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListAdapter

object RecyclerViewBindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun RecyclerView.setTravelData(items: MutableList<Travel>?){
        items?.let{
            (adapter as TravelListAdapter).submitList(it) //For ListAdapter
             Log.d("BindingAdapter",(adapter as TravelListAdapter).itemCount.toString())
        }
    }

    @BindingAdapter("listData")
    @JvmStatic
    fun RecyclerView.setDayData(items: MutableList<Day>?){
        items?.let{
            (adapter as DayListAdapter).submitList(it) //For ListAdapter
            Log.d("BindingAdapter",(adapter as DayListAdapter).itemCount.toString())
        }
    }
}