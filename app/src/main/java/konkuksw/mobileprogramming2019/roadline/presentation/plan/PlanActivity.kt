package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityPlanBinding
import konkuksw.mobileprogramming2019.roadline.global.util.HasParamViewModelFactory
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class PlanActivity : BaseActivity<ActivityPlanBinding>(
    R.layout.activity_plan
) {
    private lateinit var viewModel:PlanViewModel

    private val travelId by lazy {
        intent.getIntExtra("travelId", -1)
    }
    private var dateIconSelected: MutableLiveData<Boolean> = MutableLiveData()

    override fun initView() {
        viewModel = ViewModelProvider(this, HasParamViewModelFactory(travelId)).get(PlanViewModel::class.java)
        binding.viewModel = viewModel


        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        viewModel.travelId = travelId

        binding.btnAll.tvDateIcon.isSelected = true
        dateIconSelected.value = binding.btnAll.tvDateIcon.isSelected
        dateIconSelected.observe(this, { isSelected ->
            if(isSelected){
                viewModel.getPlansByDayId(null)
            }
        })

        binding.btnAll.onItemClickListener = object : DayListAdapter.OnItemClickListener{
            override fun onItemClick(day: Day?) {
                binding.btnAll.tvDateIcon.isSelected = true
            }
        }

        binding.rvDates.adapter = DayListAdapter(object : DayListAdapter.OnItemClickListener {
            override fun onItemClick(day: Day?) {
                viewModel.getPlansByDayId(day?.id)
            }
        })
        viewModel.travelWithDays.observe(this){
            viewModel.getPlansByDayId(null)
        }
        viewModel.travelWithPlans.observe(this){
            it.daysWithPlans.forEach { dayWithPlans ->
                dayWithPlans.plans
            }
        }
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