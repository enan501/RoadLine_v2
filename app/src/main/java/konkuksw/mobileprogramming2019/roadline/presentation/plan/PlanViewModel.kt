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

    fun getSelectedDayId(): Int? {
        daysAndPlansByTravel.value?.let { travel ->
            selectedDay.value?.let {
                return travel.daysWithPlans[it-1].day.id
            }
        }
        return null
    }

    fun deletePlan(plan: Plan) {
        viewModelScope.launch(Dispatchers.IO) {
            MyApplication.planRepo.delete(plan)
        }
    }

    fun setAllPosition(currentList: List<Plan>) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("mytag","before : "+ currentList.map { it.pos }.toString())
            for(i in currentList.indices) {
                MyApplication.planRepo.updatePlanPos(currentList[i].id!!, i)
            }
            Log.d("mytag","after : "+  currentList.map { it.pos }.toString())
        }
    }

}