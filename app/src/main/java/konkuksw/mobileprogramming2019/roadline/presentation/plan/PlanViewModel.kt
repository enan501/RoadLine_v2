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
    var travelWithDaysAndPlans = MyApplication.travelRepo.getTravelWithDaysAndPlans(travelId)
    var plans: MutableLiveData<List<Plan>> = MutableLiveData()
    var selectedDay = MutableLiveData<Int?>(null)


    fun setPlans(dayNum: Int?){
        travelWithDaysAndPlans.value?.let {
            if(dayNum == null)
                plans.postValue(it.daysWithPlans.flatMap{dwp -> dwp.plans})
            else
                plans.postValue(it.daysWithPlans[dayNum-1].plans)
        }
    }






}