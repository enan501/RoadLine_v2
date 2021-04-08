package konkuksw.mobileprogramming2019.roadline.global.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import konkuksw.mobileprogramming2019.roadline.presentation.plan.PlanViewModel

class HasParamViewModelFactory(private val param: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PlanViewModel::class.java)) {
            PlanViewModel(param) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}