package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Parcelable
import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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
import konkuksw.mobileprogramming2019.roadline.global.extension.totalMinToHour
import konkuksw.mobileprogramming2019.roadline.global.extension.totalMinToMin
import konkuksw.mobileprogramming2019.roadline.global.util.HasParamViewModelFactory
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity


class AddPlanActivity : BaseActivity<ActivityAddPlanBinding>(
    R.layout.activity_add_plan
) {
    private lateinit var viewModel: AddPlanViewModel

    private val bundleAdd by lazy {
        intent.getBundleExtra("bundle_add")
    }

    private val bundleEdit by lazy {
        intent.getBundleExtra("bundle_edit")
    }

    private val dayId by lazy {
        bundleAdd.getInt("dayId", -1)
    }

    private val pos by lazy {
        bundleAdd.getInt("pos")
    }

    private val plan by lazy {
        bundleEdit?.getParcelable<Plan>("plan")
    }

    private val autoCompleteFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragName) as AutocompleteSupportFragment
    }

    private val addMapView by lazy {
        supportFragmentManager.findFragmentById(R.id.fragMap) as SupportMapFragment
    }
    private val searchBox by lazy {
        autoCompleteFragment.view?.findViewById(R.id.places_autocomplete_search_input) as EditText
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun initView() {
        viewModel = ViewModelProvider(this, HasParamViewModelFactory(application, plan)).get(AddPlanViewModel::class.java)
        binding.viewModel = viewModel
        binding.timePicker.isEnabled = false

        if(bundleAdd != null){
            binding.toolbar.title = "일정 추가"
            binding.timePicker.isEnabled = false
        }
        if(bundleEdit != null) {
            binding.toolbar.title = "일정 수정"
            searchBox.setText(plan!!.name)
            plan!!.time?.let {
                binding.timePicker.isEnabled = true
                binding.swTime.isChecked = true
                binding.timePicker.hour = totalMinToHour(it)
                binding.timePicker.minute = totalMinToMin(it)
            }
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.swTime.setOnCheckedChangeListener { _, isChecked ->
            binding.timePicker.isEnabled = isChecked
        }

        if (!Places.isInitialized()) {
            Places.initialize(application.applicationContext, application.resources.getString(R.string.MAPS_API_KEY))
        }
        addMapView.getMapAsync(viewModel)


        binding.btnConfirm.setOnClickListener {
            var time: Int? = null
            if(binding.swTime.isChecked) {
                time = hourMinToTotalMin(binding.timePicker.hour, binding.timePicker.minute)
            }
            if(bundleAdd != null) { // 추가
                viewModel.addPlan(dayId, time, pos)
            }
            if(bundleEdit != null) {
                viewModel.editPlan(plan!!.id!!, time)
            }
            finish()
        }

        autoCompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))
        autoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(status: Status) {
                Log.d("addMap", "Error : " + status.toString())
            }

            override fun onPlaceSelected(place: Place) {
                viewModel.setPlaceSelected(place)
            }
        })
    }

}