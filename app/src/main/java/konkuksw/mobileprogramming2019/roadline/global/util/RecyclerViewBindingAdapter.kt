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
import konkuksw.mobileprogramming2019.roadline.presentation.plan.HorizontalPlanListAdapter
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

    @BindingAdapter("verticalListData", "selectedDay")
    @JvmStatic
    fun RecyclerView.setVerticalPlanData(items: TravelWithDaysAndPlans?, selectedDay: Int) {
        items?.let { it ->
            (adapter as VerticalPlanListAdapter).let { vpAdapter ->
                val originDayId = if (vpAdapter.itemCount > 0) vpAdapter.currentList[0].dayId else null

                vpAdapter.submitList(
                    if (selectedDay == 0)
                        it.daysWithPlans.flatMap { dwp -> dwp.plans }
                    else
                    // 선택된 날짜의 plan data 가져옴
                        it.daysWithPlans[selectedDay - 1].plans
                ) {
                    if (selectedDay == 0) {
                        val startPos = vpAdapter.currentList.indexOfFirst{plan -> plan.dayId == originDayId}
                        val lastPos = vpAdapter.currentList.indexOfLast{plan -> plan.dayId == originDayId}
                        vpAdapter.notifyItemChanged(startPos)
                        vpAdapter.notifyItemChanged(lastPos)
                    } else {
                        vpAdapter.notifyItemChanged(0)
                        vpAdapter.notifyItemChanged(vpAdapter.itemCount - 1)
                    }
                }
                // submitList callback 으로 notifyDatasetChanged()를 호출해주어 재활용된 뷰의 bind 함수 호출 안되는 문제 해결
                // listAdapter 의 장점을 무시하는 코드인 것 같은데 어떻게 해결하면 좋을까?
                // 개선 방안 ->
                // 1) All -> 특정 날짜로 가는 경우 마지막에 첫째 / 마지막만 갱신해주면 됨
                // 2) 특정 날짜 -> All로 가는 경우, 기존 dayId를 기준으로 첫째 / 마지막 index를 찾아서 notify 해줌
                // 1)의 경우는 괜찮겠지만 2)가 과연 효율적인 방법일 지 고민됨
            }
        }
    }

    @BindingAdapter("horizontalListData", "selectedDay")
    @JvmStatic
    fun RecyclerView.setHorizontalPlanData(items: TravelWithDaysAndPlans?, selectedDay: Int) {
        items?.let { it ->
            (adapter as HorizontalPlanListAdapter).let { vpAdapter ->
                val originDayId = if (vpAdapter.itemCount > 0) vpAdapter.currentList[0].dayId else null

                vpAdapter.submitList(
                    if (selectedDay == 0)
                        it.daysWithPlans.flatMap { dwp -> dwp.plans }
                    else
                    // 선택된 날짜의 plan data 가져옴
                        it.daysWithPlans[selectedDay - 1].plans
                ) {
                    if (selectedDay == 0) {
                        val startPos = vpAdapter.currentList.indexOfFirst{plan -> plan.dayId == originDayId}
                        val lastPos = vpAdapter.currentList.indexOfLast{plan -> plan.dayId == originDayId}
                        vpAdapter.notifyItemRangeChanged(startPos, lastPos-startPos+1)
                    } else {
                        vpAdapter.notifyItemRangeChanged(0, vpAdapter.itemCount)
                    }
                }
                // submitList callback 으로 notifyDatasetChanged()를 호출해주어 재활용된 뷰의 bind 함수 호출 안되는 문제 해결
                // listAdapter 의 장점을 무시하는 코드인 것 같은데 어떻게 해결하면 좋을까?
                // 개선 방안 ->
                // 1) All -> 특정 날짜로 가는 경우 마지막에 첫째 / 마지막만 갱신해주면 됨
                // 2) 특정 날짜 -> All로 가는 경우, 기존 dayId를 기준으로 첫째 / 마지막 index를 찾아서 notify 해줌
                // 1)의 경우는 괜찮겠지만 2)가 과연 효율적인 방법일 지 고민됨
            }
        }
    }
}