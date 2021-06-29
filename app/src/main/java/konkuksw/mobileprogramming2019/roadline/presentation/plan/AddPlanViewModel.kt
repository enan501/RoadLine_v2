package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class AddPlanViewModel(application: Application) : BaseViewModel(application) , OnMapReadyCallback {
    lateinit var googleMap: GoogleMap
    private val markerIcon: Bitmap
    private var spotName = ""
    private var locationX = 0.0
    private var locationY = 0.0

    init {
        val bitmap = (ContextCompat.getDrawable(application.applicationContext, R.drawable.marker) as BitmapDrawable).bitmap
        markerIcon = Bitmap.createScaledBitmap(bitmap, 71, 100, false)
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0

    }
    
    fun setPlaceSelected(place: Place) {
        googleMap.clear()
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng,12f))

        val marker = MarkerOptions()
        marker.position(place.latLng!!)
            .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
        googleMap.addMarker(marker)
        spotName = place.name.toString()
        locationY = (place.latLng as LatLng).latitude
        locationX = (place.latLng as LatLng).longitude
    }

    fun addPlan(dayId: Int, nameAlter: String, time: Long, memo: String, pos: Int) {
        viewModelScope.launch {
            val plan = Plan(
                dayId = dayId,
                name = spotName,
                nameAlter = nameAlter,
                locationX = locationX,
                locationY = locationY,
                time = time,
                memo = memo,
                pos = pos
            )
            MyApplication.planRepo.insert(plan)
        }
    }

}