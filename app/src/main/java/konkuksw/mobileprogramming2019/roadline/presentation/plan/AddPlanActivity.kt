package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityAddPlanBinding
import konkuksw.mobileprogramming2019.roadline.global.extension.hourMinToTotalMin
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity


class AddPlanActivity : BaseActivity<ActivityAddPlanBinding>(
    R.layout.activity_add_plan
) , OnMapReadyCallback {
    private val viewModel:AddPlanViewModel by viewModels()
    lateinit var googleMap: GoogleMap
    private val travelId by lazy {
        intent.getIntExtra("travelId", -1)
    }

    private val dayId by lazy {
        intent.getIntExtra("dayId", -1)
    }

    private val plan by lazy {
        intent.getParcelableExtra<Plan>("plan")
    }
    private val autoCompleteFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragName) as AutocompleteSupportFragment
    }

    private val addMapView by lazy {
        supportFragmentManager.findFragmentById(R.id.fragMap) as SupportMapFragment
    }


    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        initMap()
//        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun initView() {
        binding.viewModel = viewModel
        if(plan == null){
            binding.toolbar.title = "일정 추가"
            binding.timePicker.isEnabled = false
        }
        else{
            binding.toolbar.title = "일정 수정"
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.swTime.setOnCheckedChangeListener { _, isChecked ->
            binding.timePicker.isEnabled = isChecked
        }

        if (!Places.isInitialized()) {
            Places.initialize(application.applicationContext, application.resources.getString(R.string.api_key))
        }
        addMapView.getMapAsync(this)
        autoCompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))
        autoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(status: Status) {
                Log.d("addMap", "Error : " + status.toString())
            }

            override fun onPlaceSelected(place: Place) {
                viewModel.setPlaceSelected(googleMap, place)
            }
        })

        binding.btnConfirm.setOnClickListener {
            var time: Int? = null
            if(binding.swTime.isChecked) {
                time = hourMinToTotalMin(binding.timePicker.hour, binding.timePicker.minute)
            }
            if(viewModel.spotName.isNotEmpty()) {
                if(plan == null) { // 추가
                    viewModel.addPlan(
                        dayId,
                        binding.titleEditText.text.toString(),
                        time,
                        binding.memoEditText.text.toString()
                    )
                }
                else{ // 수정
                    viewModel.editPlan(
                        plan.id!!,
                        binding.titleEditText.text.toString(),
                        time,
                        binding.memoEditText.text.toString()
                    )

                }
                finish()
            }
        }
    }

    private fun initMap() {
        if(plan == null) { // 추가
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.552420, 126.984719),12f))
        }
        else { // 수정

        }
    }

    override fun setObserve() {
        super.setObserve()


    }
}