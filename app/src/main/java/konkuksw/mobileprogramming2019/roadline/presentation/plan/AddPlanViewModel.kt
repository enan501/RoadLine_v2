package konkuksw.mobileprogramming2019.roadline.presentation.plan

import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AddPlanViewModel : BaseViewModel() {

    fun addPlan(day: Day) {
        viewModelScope.launch {
            MyApplication.dayRepo.insert(day)
        }
    }

}