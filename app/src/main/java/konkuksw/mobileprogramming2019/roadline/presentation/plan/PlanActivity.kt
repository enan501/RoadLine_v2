package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class PlanActivity : BaseActivity<ActivityPlanBinding>(
    R.layout.activity_plan
) {
    private val viewModel:PlanViewModel by viewModels()
    private val allDateIcon: TextView by lazy {
        binding.btnAll.findViewById(R.id.tvDateIcon)
    }

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnAll.setOnClickListener {
            allDateIcon.isSelected = true
            allDateIcon.background = resources.getDrawable(R.drawable.background_circle, null)
        }
        binding.rvDates.adapter
        val travelId = intent.getIntExtra("travelId", -1)
        viewModel.getDataFromDB(travelId)
//        viewModel.travelWithDays.observe(this, {
//        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuPhoto -> {

            }
            R.id.menuMoney -> {

            }
            R.id.menuShare -> {

            }
            android.R.id.home -> {
                finish()
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }


}