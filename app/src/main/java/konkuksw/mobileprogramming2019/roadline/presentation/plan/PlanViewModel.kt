package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanViewModel(application: Application, val travelId: Int) : BaseViewModel(application) {

    var daysAndPlansByTravel = MyApplication.travelRepo.getTravelWithDaysAndPlans(travelId)
    var selectedDay = MutableLiveData(0)
    val editMode = MutableLiveData(false)

    fun getSelectedDayId(): Int {
        daysAndPlansByTravel.value?.let { travel ->
            return travel.daysWithPlans[selectedDay.value!!-1].day.id!!
        }
        return -1
    }

    fun deletePlan(plan: Plan) {
        viewModelScope.launch(Dispatchers.IO) {
            MyApplication.planRepo.delete(plan)
        }
    }


    fun getPlansCountBySelectedDay(): Int {
        daysAndPlansByTravel.value?.let {
            return it.daysWithPlans[selectedDay.value!! - 1].plans.size
        }
        return 0
    }

    fun updatePosition(currentList: List<Plan>, startPos: Int, endPos: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            MyApplication.planRepo.updatePlanPosRange(currentList, startPos, endPos)
            if(startPos < endPos) {
                MyApplication.planRepo.updatePlanPosRange(currentList, startPos, endPos)
            }
            else{
                MyApplication.planRepo.updatePlanPosRange(currentList, endPos, startPos)
            }
        }
    }
}