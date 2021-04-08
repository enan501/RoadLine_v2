package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentMapPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseFragment

class MapPlanFragment : BaseFragment<FragmentMapPlanBinding>(
    R.layout.fragment_map_plan
) , OnMapReadyCallback {
    val planViewModel: PlanViewModel by activityViewModels()
    lateinit var mapView:MapView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = binding.googleMap
        mapView.onCreate(Bundle.EMPTY)
        mapView.getMapAsync(this)
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onMapReady(p0: GoogleMap?) {
        Log.d("MapPlanFragment", "ready")
    }
}