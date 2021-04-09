package konkuksw.mobileprogramming2019.roadline.presentation.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel

class PlanViewModel(val travelId: Int) : BaseViewModel() {
    var travelWithDays = MyApplication.travelRepo.getTravelWithDays(travelId)
    var dayWithPlansList: ArrayList<LiveData<DayWithPlans>> = arrayListOf()
    var travelWithPlans = MyApplication.travelRepo.getTravelWithDaysAndPlans(travelId)

    fun getPlansByDayId(dayId: Int?) {
        dayWithPlansList.clear()
        if (dayId == null) {
            // 모든 day

            travelWithDays.value?.days?.forEach{day ->
                    dayWithPlansList.add(MyApplication.dayRepo.getDayWithPlans(day.id!!))
            }
        }
        else {
            dayWithPlansList.add(MyApplication.dayRepo.getDayWithPlans(dayId))
        }
    }








}