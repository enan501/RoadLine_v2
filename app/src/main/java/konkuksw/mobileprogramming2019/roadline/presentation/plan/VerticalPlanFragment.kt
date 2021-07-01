package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentVerticalPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseFragment


class VerticalPlanFragment : BaseFragment<FragmentVerticalPlanBinding>(
    R.layout.fragment_vertical_plan
) {
    val planViewModel: PlanViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.viewModel = planViewModel
        binding.rvVerticalPlan.adapter = VerticalPlanListAdapter(planViewModel,requireActivity(),
        object: VerticalPlanListAdapter.OnItemClickListener{
            override fun onItemClick(plan: Plan) {
                // go to AddPlan (edit)
                val intent = Intent(activity, AddPlanActivity::class.java)
                intent.putExtra("plan", plan)
                startActivity(intent)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }
}