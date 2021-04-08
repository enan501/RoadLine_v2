package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.appbar.AppBarLayout
import konkuksw.mobileprogramming2019.roadline.BuildConfig
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
        binding.rvTravel.adapter = TravelListAdapter(object : TravelListAdapter.OnItemClickListener {
                override fun onItemClick(travel: Travel) {
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
                    .setMessage("dd")
                    .setOkButton("추가") {
                        viewModel.addTravel(Travel(title = "배낭여행", dateStart = LocalDate.MIN, dateEnd = LocalDate.MAX))
                        dialog.dismissDialog()
                    }
                    .show()
        }

        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{ _: AppBarLayout?, verticalOffset: Int ->
            binding.tvSubTitle.alpha = if (verticalOffset == 0) 1f else if (verticalOffset < -200) 0f else (-20f / verticalOffset)
        })

        binding.btnSetting.setOnClickListener { binding.drawerLayout.openDrawer(binding.settingView)  }
        binding.settingView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.btnRefreshCurrency->{
//                    refreshCurrency(this)
                }
                R.id.btnRate -> {
                    try {
                        val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                        startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                        )
                        startActivity(intent)
                    }
                }
                R.id.btnVersionInfo -> {
                    var dialog = BaseDialog.Builder(this@TravelListActivity).create()
                    dialog.setTitle("버전 정보")
                            .setMessage("Ver ${BuildConfig.VERSION_NAME}")
                            .setOkButton("닫기", View.OnClickListener { dialog.dismissDialog() })
                            .show()
                }
            }
            binding.drawerLayout.closeDrawer(binding.settingView)
            true
        }
    }

}