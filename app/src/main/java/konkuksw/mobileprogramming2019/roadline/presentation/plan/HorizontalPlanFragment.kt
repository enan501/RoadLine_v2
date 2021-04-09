package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentHorizontalPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseFragment

class HorizontalPlanFragment : BaseFragment<FragmentHorizontalPlanBinding>(
    R.layout.fragment_horizontal_plan
) {
    val planViewModel: PlanViewModel by activityViewModels()

}