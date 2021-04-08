package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListAdapter

class PlanActivity : BaseActivity<ActivityPlanBinding>(
    R.layout.activity_plan
) {
    private val viewModel:PlanViewModel by viewModels()
    private val travelId by lazy {
        intent.getIntExtra("travelId", -1)
    }
    private var dateIconSelected: MutableLiveData<Boolean> = MutableLiveData()

    private val planFragments = arrayListOf<Fragment>(
        VerticalPlanFragment(),
        HorizontalPlanFragment(),
        MapPlanFragment()
    )

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnAll.tvDateIcon.isSelected = true
        dateIconSelected.value = binding.btnAll.tvDateIcon.isSelected
        dateIconSelected.observe(this, { isSelected ->
            if(isSelected){
                viewModel.getAllDataFromDB(travelId)
                viewModel.travelWithDays.observe(this@PlanActivity, {
                    it.days
                })
            }
        })

        binding.btnAll.onItemClickListener = object : DayListAdapter.OnItemClickListener{
            override fun onItemClick(day: Day?) {
                binding.btnAll.tvDateIcon.isSelected = true
            }
        }

        binding.rvDates.adapter = DayListAdapter(object : DayListAdapter.OnItemClickListener {
            override fun onItemClick(day: Day?) {

            }
        })


        binding.vpPlans.adapter = PlanViewPagerAdapter(planFragments, this)
        TabLayoutMediator(binding.tabs,binding.vpPlans){tab, position ->
            tab.icon = when(position){
                0->{ContextCompat.getDrawable(this,R.drawable.tab_list)}
                1->{ContextCompat.getDrawable(this,R.drawable.tab_timeline)}
                else->{ContextCompat.getDrawable(this,R.drawable.tab_map)}
            }
        }.attach()
        for(i in 0..2)
            binding.tabs.getTabAt(i)?.apply{view.alpha = 0.4F}
        binding.tabs.addOnTabSelectedListener(object:TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.alpha = 1F
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.alpha = 0.4F
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.view?.alpha = 1F
            }

        })

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