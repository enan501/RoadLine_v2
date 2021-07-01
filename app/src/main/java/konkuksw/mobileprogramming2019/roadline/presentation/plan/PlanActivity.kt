package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityPlanBinding
import konkuksw.mobileprogramming2019.roadline.global.util.HasParamViewModelFactory
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class PlanActivity : BaseActivity<ActivityPlanBinding>(
    R.layout.activity_plan
) {
    private lateinit var viewModel: PlanViewModel

    private val planFragments = arrayListOf<Fragment>(
        VerticalPlanFragment(),
        HorizontalPlanFragment(),
        MapPlanFragment()
    )
    private val travelId by lazy {
        intent.getIntExtra("travelId", -1)
    }
    private val onDayClickListener = object : DayListAdapter.OnItemClickListener {
        override fun onItemClick(dayNum: Int?) {
            viewModel.selectedDay.postValue(dayNum)

        }
    }


    override fun initView() {
        viewModel = ViewModelProvider(this, HasParamViewModelFactory(travelId)).get(PlanViewModel::class.java)
        binding.viewModel = viewModel

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnAll.dayNum = 0
        binding.btnAll.onItemClickListener = onDayClickListener
        binding.rvDates.adapter = DayListAdapter(viewModel, this, onDayClickListener)

        binding.vpPlans.adapter = PlanViewPagerAdapter(planFragments, this)
        TabLayoutMediator(binding.tabs,binding.vpPlans){tab, position ->
            tab.icon = when(position){
                0->{ ContextCompat.getDrawable(this,R.drawable.tab_list)}
                1->{ ContextCompat.getDrawable(this,R.drawable.tab_timeline)}
                else->{ ContextCompat.getDrawable(this,R.drawable.tab_map)}
            }
            tab.view.alpha = 0.4f
        }.attach()

        binding.tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) { tab?.view?.alpha = 1f }
            override fun onTabUnselected(tab: TabLayout.Tab?) { tab?.view?.alpha = 0.4f }
            override fun onTabReselected(tab: TabLayout.Tab?) { tab?.view?.alpha = 1f }
        })
        binding.fabAdd.setOnClickListener {
            // 일정 추가
            val intent = Intent(this@PlanActivity, AddPlanActivity::class.java)
            intent.putExtra("travelId", travelId)
            intent.putExtra("dayId", viewModel.getSelectedDayId())
            startActivity(intent)
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

    override fun setObserve() {
        viewModel.getAllPlans().observe(this, { dayWithPlans ->
            Log.d("mytag", dayWithPlans.toString())

        })
    }

}