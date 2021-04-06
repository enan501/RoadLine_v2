package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivityPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity

class PlanActivity : BaseActivity<ActivityPlanBinding>(
    R.layout.activity_splash
) {
    private val viewModel:PlanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}