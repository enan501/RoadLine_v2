package konkuksw.mobileprogramming2019.roadline.presentation.plan

import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityAddPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class AddPlanActivity : BaseActivity<ActivityAddPlanBinding>(
    R.layout.activity_add_plan
) {
    private val viewModel:AddPlanViewModel by viewModels()
}