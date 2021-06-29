package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityAddPlanBinding
import konkuksw.mobileprogramming2019.roadline.global.extension.hourMinToTotalMin
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity
import java.util.*

class AddPlanActivity : BaseActivity<ActivityAddPlanBinding>(
    R.layout.activity_add_plan
)  {
    private val viewModel:AddPlanViewModel by viewModels()
    private val travelId by lazy {
        intent.getIntExtra("travelId", -1)
    }

    private val dayId by lazy {
        intent.getIntExtra("dayId", -1)
    }
    private val autoCompleteFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragName) as AutocompleteSupportFragment
    }


//    override fun onMapReady(p0: GoogleMap) {
////        initMap()
////        initListener()
//    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

//    fun initMap(){
//        if(planId != null){ //수정
//            searchBox.setText(thisPlan!!.name)
//            var title = ""
//            if(spotNameAlter == null){
//                title = spotName
//            }
//            else{
//                title = spotNameAlter!!
//            }
//            var str: String? = null
//            if(hour != null){
//                val minute = minute.toString()
//                str = hour.toString()
//                if(minute.length == 1)
//                    str += "시 0" + minute + "분"
//                else
//                    str += "시 " + minute + "분"
//
//            }
//
//            addMap.addMarker(
//                MarkerOptions()
//                    .position(LatLng(locationY,locationX))
//                    .title(title)
//                    .snippet(str)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
//            )
//            addMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(locationY,locationX),12f))
//        }
//        else{ //추가
//            addMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(37.552420, 126.984719),12f))
//        }
//    }

//    fun initListener(){
//        autocompleteFragment!!.setOnPlaceSelectedListener(object : PlaceSelectionListener {
//            override fun onError(status: Status) {
//                Log.d("addMap", "Error : " + status.toString())
//            }
//
//            override fun onPlaceSelected(place: Place) {
//                addMap.clear()
//                addMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place.latLng,12f))
//
//                var marker = MarkerOptions()
//                marker.position(place.latLng!!)
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerIcon))
//                addMap.addMarker(marker)
//                spotName = place.name.toString()
//                locationY = (place.latLng as LatLng).latitude
//                locationX = (place.latLng as LatLng).longitude
//            }
//        })
//
//        as_button_check.setOnClickListener { //체크 등록 버튼
//            if(searchBox.text.toString() != ""){
//                if(planId == null) { //추가
//                    realm.beginTransaction()
//                    val plan: T_Plan = realm.createObject(T_Plan::class.java, UUID.randomUUID().toString())
//                    plan.listID = listID
//                    plan.dayNum = DayNum
//                    plan.name = spotName
//                    plan.nameAlter = spotNameAlter
//                    plan.hour = hoursearchBox
//                    plan.minute = minute
//                    plan.memo = memo
//                    plan.locationX = locationX
//                    plan.locationY = locationY
//                    plan.pos = pos
//                    realm.commitTransaction()
//                }
//                else { //수정
//                    realm.beginTransaction()
//                    thisPlan!!.name = spotName
//                    thisPlan!!.nameAlter = spotNameAlter
//                    thisPlan!!.hour = hour
//                    thisPlan!!.minute = minute
//                    thisPlan!!.memo = memo
//                    thisPlan!!.locationX = locationX
//                    thisPlan!!.locationY = locationY
//                    realm.commitTransaction()
//                }
//                val s = Intent()
//                s.putExtra("dayNum", intent.getIntExtra("DayNum", -1))
//                s.putExtra("listId", intent.getStringExtra("ListID"))
//                setResult(Activity.RESULT_OK, s)
//
//                finish()
//            }
//            else{ //아무값 입력하지 않으면
//
//                val builder = BaseDialog.Builder(this).create()
//                builder.setTitle("알림")
//                    .setMessage("위치를 추가하세요")
//                    .setCancelButton("확인")
//                    .show()
//            }
//        }
//        path_bt.setOnClickListener {
//            var prev = realm.where(T_Plan::class.java).equalTo("listID",listID).equalTo("dayNum",DayNum).equalTo("pos", pos - 1).findFirst()!!
//            var cur = thisPlan
//
//            var uri = "http://maps.google.com/maps?saddr="+prev.locationY+","+prev.locationX+"&daddr="+cur!!.locationY+","+ cur.locationX+"&dirflg=r"
//            var mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
//            mapIntent.setPackage("com.google.android.apps.maps")
//            startActivity(mapIntent)
//        }
//
//        dialogCheckMemo.setOnCheckedChangeListener { buttonView, isChecked ->
//            when(isChecked){
//                true->dialogMemo.isEnabled = true
//                false->dialogMemo.isEnabled = false
//            }
//        }
//
//        dialogCheckTime.setOnCheckedChangeListener { buttonView, isChecked ->
//            when(isChecked){
//                true->dialogTime.isEnabled = true
//                false->dialogTime.isEnabled = false
//            }
//        }
//
//
//        memo_button.setOnClickListener {
//            if(spotNameAlter == null){
//                if(spotName == ""){
//                    dialogTitle.text.clear()
//                    dialogTitle.hint = "위치를 추가하세요"
//                    dialogTitle.isEnabled = false
//                }
//                else{
//                    dialogTitle.hint = spotName
//                    dialogTitle.isEnabled = true
//                    dialogTitle.setText(spotName)
//                }
//            }
//            else{
//                dialogTitle.hint = spotName
//                dialogTitle.isEnabled = true
//                dialogTitle.setText(spotNameAlter)
//            }
//            if(hour != null){
//                dialogTime.hour = hour!!
//                dialogTime.minute = minute!!
//                dialogCheckTime.isChecked = true
//                dialogTime.isEnabled = true
//            }else{
//                dialogTime.hour = 0
//                dialogTime.minute = 0
//                dialogCheckTime.isChecked = false
//                dialogTime.isEnabled = false
//            }
//            if(memo != null){
//                dialogMemo.setText(memo)
//                dialogCheckMemo.isChecked = true
//                dialogMemo.isEnabled = true
//
//            }else{
//                dialogMemo.text.clear()
//                dialogCheckMemo.isChecked = false
//                dialogMemo.isEnabled  = false
//            }
//            dialogTitle.setSelection(dialogTitle.text.length)
//            if(addDialog.parent != null){
//                (addDialog.parent as ViewGroup).removeView(addDialog)
//            }
//            builder.show()
//        }
//
//        builder.setPositiveButton("추가") { dialogInterface, i ->
//            if(dialogTitle.text.toString() != ""){
//                spotNameAlter = dialogTitle.text.toString()
//            } else{
//                spotNameAlter = null
//            }
//            if(dialogCheckMemo.isChecked){
//                if(dialogMemo.text.isNotEmpty())
//                    memo = dialogMemo.text.toString()
//                else
//                    memo = null
//            } else{
//                memo = null
//            }
//            if(dialogCheckTime.isChecked){
//                hour = dialogTime.hour
//                minute = dialogTime.minute
//            } else{
//                hour = null
//                minute = null
//            }
//        }
//            .setNegativeButton("취소") { dialogInterface, i -> }
//
//        replace_bt.setOnClickListener {
//            if(addDialog.parent != null){
//                (addDialog.parent as ViewGroup).removeView(addDialog)
//            }
//            getCurLoc()
//        }
//    }

    override fun initView() {
        binding.viewModel = viewModel
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val addMapView = supportFragmentManager.findFragmentById(R.id.fragMap) as SupportMapFragment
        addMapView.getMapAsync(viewModel)

        autoCompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))
        autoCompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(status: Status) {
                Log.d("addMap", "Error : " + status.toString())
            }

            override fun onPlaceSelected(place: Place) {
                viewModel.setPlaceSelected(place)
            }
        })


        binding.btnConfirm.setOnClickListener {
            viewModel.addPlan(
                dayId,
                binding.titleEditText.text.toString(),
                hourMinToTotalMin(binding.timePicker.hour, binding.timePicker.minute),
                binding.memoEditText.text.toString(),
            )
            finish()
        }
    }

    override fun setObserve() {
        super.setObserve()
    }
}