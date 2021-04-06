package konkuksw.mobileprogramming2019.roadline.presentation.travelList

import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityTravelListBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class TravelListActivity : BaseActivity<ActivityTravelListBinding>(
    R.layout.activity_travel_list
) {
    private val viewModel: TravelListViewModel by viewModels()
}