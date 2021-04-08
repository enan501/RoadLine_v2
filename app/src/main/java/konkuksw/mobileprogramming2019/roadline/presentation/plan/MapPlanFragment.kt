package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentHorizontalPlanBinding
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentMapPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseFragment

class MapPlanFragment : BaseFragment<FragmentMapPlanBinding>(
    R.layout.fragment_map_plan
) {
    val planViewModel: PlanViewModel by activityViewModels()
}