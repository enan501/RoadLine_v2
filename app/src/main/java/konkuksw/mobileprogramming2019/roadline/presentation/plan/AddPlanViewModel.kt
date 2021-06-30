package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddPlanViewModel(application: Application) : BaseViewModel(application){
    private val markerBitmap = (ContextCompat.getDrawable(application.applicationContext, R.drawable.marker) as BitmapDrawable).bitmap
    var spotName = ""
    private var locationX = 0.0
    private var locationY = 0.0

    fun setPlaceSelected(googleMap: GoogleMap, place: Place) {
        val markerIcon = Bitmap.createScaledBitmap(markerBitmap, 71, 100, false)
        googleMap.clear()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng,12f))
        googleMap.addMarker(MarkerOptions().position(place.latLng!!).icon(BitmapDescriptorFactory.fromBitmap(markerIcon)))
        spotName = place.name.toString()
        locationY = (place.latLng as LatLng).latitude
        locationX = (place.latLng as LatLng).longitude
    }

    fun addPlan(dayId: Int, nameAlter: String, time: Int?, memo: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            val plan = Plan(
                dayId = dayId,
                name = spotName,
                nameAlter = nameAlter,
                locationX = locationX,
                locationY = locationY,
                time = time,
                memo = memo,
                pos = getPlansCountByDayId(dayId)
            )
            MyApplication.planRepo.insert(plan)
        }
    }

    fun editPlan(planId: Int, nameAlter: String, time: Int?, memo: String?) {
        viewModelScope.launch {
            MyApplication.planRepo.updatePlan(
                    planId = planId,
                    name = spotName,
                    nameAlter = nameAlter,
                    locationX = locationX,
                    locationY = locationY,
                    time = time,
                    memo = memo,
            )
        }
    }

    private fun getPlansCountByDayId(dayId: Int): Int {
        MyApplication.dayRepo.getDayWithPlans(dayId).value?.let {
            return it.plans.size
        }
        return 0
    }

    fun getAllPlans(dayId: Int): LiveData<DayWithPlans> {
        return MyApplication.dayRepo.getDayWithPlans(dayId)
    }

}