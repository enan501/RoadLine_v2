package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityTravelListBinding
import konkuksw.mobileprogramming2019.roadline.global.widget.BaseDialog
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity
import java.time.LocalDate

class TravelListActivity : BaseActivity<ActivityTravelListBinding>(
    R.layout.activity_travel_list
) {
    private val viewModel: TravelListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.rvTravel.adapter = TravelListAdapter(object:TravelListAdapter.OnItemClickListener{
            override fun onItemClick(travel: Travel) {
                Log.d("TravelActivity", travel.title)
            }

            override fun onEditClick(travel: Travel) {
                Log.d("TravelActivity", travel.title)
                val dialog = BaseDialog.Builder(this@TravelListActivity)
                dialog.create()
                        .setTitle("수정")
                        .setOkButton("확인") {
                            viewModel.editTravel(travel)
                            dialog.dismissDialog()
                        }
                        .show()
            }

            override fun onDeleteClick(travel: Travel) {
                Log.d("TravelActivity", travel.title)
            }
        })

        binding.btnAddTravel.setOnClickListener {
            val dialog = BaseDialog.Builder(this@TravelListActivity)
            dialog.create()
                    .setTitle("추가")
                    .setOkButton("추가") {
                        Log.d("TravelListActivity","onClickAdd")
                        viewModel.addTravel(Travel(title="배낭여행",dateStart = LocalDate.MIN,dateEnd = LocalDate.MAX))}
                    .show()
        }
    }

}