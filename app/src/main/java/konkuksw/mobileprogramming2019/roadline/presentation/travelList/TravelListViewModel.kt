package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TravelListViewModel : BaseViewModel() {

    val travels = MyApplication.travelRepo.getAll()

    fun addTravel(travel: Travel){
        viewModelScope.launch(Dispatchers.IO) {
            MyApplication.travelRepo.insert(travel)
        }
    }

    fun editTravel(travel: Travel){
    }
}