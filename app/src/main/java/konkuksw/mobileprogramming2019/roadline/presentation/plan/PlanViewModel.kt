package konkuksw.mobileprogramming2019.roadline.presentation.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel

class PlanViewModel : BaseViewModel() {
    lateinit var travelWithDays: LiveData<TravelWithDays>
    lateinit var days: List<Day>
    lateinit var travel: Travel

    fun getAllDataFromDB(travelId: Int){
        travelWithDays = MyApplication.travelRepo.getTravelWithDays(travelId)

    }


}