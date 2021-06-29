package konkuksw.mobileprogramming2019.roadline.presentation.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel

class PlanViewModel(val travelId: Int) : BaseViewModel() {
    var daysAndPlansByTravel = MyApplication.travelRepo.getTravelWithDaysAndPlans(travelId)
    val plansBySelectedDay: MutableLiveData<List<Plan>> by lazy {
        MutableLiveData<List<Plan>>()
    }
    var selectedDay = MutableLiveData<Int?>(null)


    fun setPlans(dayNum: Int?){
        daysAndPlansByTravel.value?.let {
            // 모든 날짜의 plan data 가져옴
            if(dayNum == null)
                plansBySelectedDay.postValue(it.daysWithPlans.flatMap{dwp -> dwp.plans})
            // 선택된 날짜의 plan data 가져
            else
                plansBySelectedDay.postValue(it.daysWithPlans[dayNum-1].plans)
        }
    }








}