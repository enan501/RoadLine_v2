package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityTravelListBinding
import konkuksw.mobileprogramming2019.roadline.global.widget.BaseDialog
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity
import konkuksw.mobileprogramming2019.roadline.presentation.plan.PlanActivity
import java.time.LocalDate

class TravelListActivity : BaseActivity<ActivityTravelListBinding>(
        R.layout.activity_travel_list
) {
    private val viewModel: TravelListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.rvTravel.adapter = TravelListAdapter(object : TravelListAdapter.OnItemClickListener {
            override fun onItemClick(travel: Travel) {
                val intent = Intent(this@TravelListActivity, PlanActivity::class.java)
                intent.putExtra("travelId", travel.id)
                startActivity(intent)
            }

            override fun onEditClick(travel: Travel) {
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
            }
        })

        binding.btnAddTravel.setOnClickListener {
            val dialog = BaseDialog.Builder(this@TravelListActivity)
            dialog.create()
                    .setTitle("추가")
                    .setOkButton("추가") {
                        viewModel.addTravel(Travel(title = "배낭여행", dateStart = LocalDate.now(), dateEnd = LocalDate.now().plusDays(6)))
                        dialog.dismissDialog()
                    }
                    .show()
        }
    }

}