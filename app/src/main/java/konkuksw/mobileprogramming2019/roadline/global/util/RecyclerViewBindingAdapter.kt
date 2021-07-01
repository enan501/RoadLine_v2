package konkuksw.mobileprogramming2019.roadline.global.util

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDaysAndPlans
import konkuksw.mobileprogramming2019.roadline.presentation.plan.DayListAdapter
import konkuksw.mobileprogramming2019.roadline.presentation.plan.VerticalPlanListAdapter
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListAdapter

object RecyclerViewBindingAdapter {
    @BindingAdapter("listData")
    @JvmStatic
    fun RecyclerView.setTravelData(items: MutableList<Travel>?){
        items?.let{
            (adapter as TravelListAdapter).submitList(it) //For ListAdapter
        }
    }

    @BindingAdapter("listData")
    @JvmStatic
    fun RecyclerView.setDayData(items: TravelWithDaysAndPlans?){
        items?.daysWithPlans?.let{ dayWithPlans ->
            (adapter as DayListAdapter).submitList(dayWithPlans.map { it.day }) //For ListAdapter
        }
    }

//    @BindingAdapter("listData")
//    @JvmStatic
//    fun RecyclerView.setVerticalPlanData(items: LiveData<List<Plan>>?){
//        items?.let{ plans ->
//            (adapter as VerticalPlanListAdapter).submitList(plans.value) //For ListAdapter
//        }
//    }

    @BindingAdapter("listData", "selectedDay")
    @JvmStatic
    fun RecyclerView.setVerticalPlanData(items: TravelWithDaysAndPlans?, selectedDay: Int){
        items?.let{ it ->
            (adapter as VerticalPlanListAdapter).submitList(
                if(selectedDay == 0)
                    it.daysWithPlans.flatMap{dwp -> dwp.plans}
                else
                // 선택된 날짜의 plan data 가져옴
                    it.daysWithPlans[selectedDay-1].plans
            )

        }
    }
}