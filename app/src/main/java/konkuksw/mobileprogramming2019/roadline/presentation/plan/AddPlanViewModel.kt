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

class AddPlanViewModel(application: Application, var plan: Plan?) : BaseViewModel(application), OnMapReadyCallback {
    private val markerBitmap = (ContextCompat.getDrawable(application.applicationContext, R.drawable.marker) as BitmapDrawable).bitmap
    var spotName = plan?.name
    private var locationX = 126.984719
    private var locationY = 37.552420
    val nameAlter = MutableLiveData<String>(plan?.nameAlter)
    val memo = MutableLiveData<String>(plan?.memo)
    lateinit var googleMap: GoogleMap
    private val markerIcon = Bitmap.createScaledBitmap(markerBitmap, 71, 100, false)


    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        if(plan == null) { // 추가
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(locationY, locationX),12f))

        }
        else{
            googleMap.addMarker(MarkerOptions()
                    .position(LatLng(plan!!.locationY, plan!!.locationX))
                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon)))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(plan!!.locationY, plan!!.locationX),12f))
        }

    }

    fun setPlaceSelected(place: Place) {
        if(::googleMap.isInitialized) {
            googleMap.clear()
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng,12f))
            googleMap.addMarker(MarkerOptions().position(place.latLng!!).icon(BitmapDescriptorFactory.fromBitmap(markerIcon)))
        }
        spotName = place.name.toString()
        locationY = (place.latLng as LatLng).latitude
        locationX = (place.latLng as LatLng).longitude
    }

    fun addPlan(dayId: Int, time: Int?) {
        spotName?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val plan = Plan(
                    dayId = dayId,
                    name = it,
                    nameAlter = nameAlter.value,
                    locationX = locationX,
                    locationY = locationY,
                    time = time,
                    memo = memo.value,
                    pos = getPlansCountByDayId(dayId)
                )
                MyApplication.planRepo.insert(plan)
            }
        }

    }

    fun editPlan(planId: Int, time: Int?) {
        spotName?.let {
            viewModelScope.launch(Dispatchers.IO) {
                MyApplication.planRepo.updatePlan(
                    planId = planId,
                    name = it,
                    nameAlter = nameAlter.value,
                    locationX = locationX,
                    locationY = locationY,
                    time = time,
                    memo = memo.value,
                )
            }
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