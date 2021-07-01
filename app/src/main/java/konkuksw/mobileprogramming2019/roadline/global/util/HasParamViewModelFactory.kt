package konkuksw.mobileprogramming2019.roadline.global.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.presentation.plan.AddPlanViewModel
import konkuksw.mobileprogramming2019.roadline.presentation.plan.PlanViewModel

class HasParamViewModelFactory<G>(private val application: Application, private val param: G) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PlanViewModel::class.java)) {
            PlanViewModel(application, param as Int) as T
        }
        else if (modelClass.isAssignableFrom(AddPlanViewModel::class.java)){
            AddPlanViewModel(application, param as Plan?) as T
        }
        else {
            throw IllegalArgumentException()
        }
    }
}