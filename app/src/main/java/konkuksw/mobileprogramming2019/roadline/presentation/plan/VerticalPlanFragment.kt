package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
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
        binding.rvVerticalPlan.adapter = VerticalPlanListAdapter(planViewModel, requireActivity(),
        object: VerticalPlanListAdapter.OnItemClickListener{
            override fun onItemClick(plan: Plan) {
                // go to AddPlan (edit)
                if(planViewModel.editMode.value!!){
                    planViewModel.editMode.value = false
                }
                else{
                    val intent = Intent(activity, AddPlanActivity::class.java)
                    intent.putExtra("plan", plan)
                    startActivity(intent)
                }

            }

            override fun onItemLongClick(plan: Plan): Boolean {
                planViewModel.editMode.value = true
                return true
            }

            override fun onItemDrag(plan: Plan) {
                Log.d("mytag", plan.toString())
            }
        })

        binding.parentLayout.setOnClickListener {
            planViewModel.editMode.value = false
        }



        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        if(planViewModel.editMode.value!!){
            planViewModel.editMode.value = false
        }

    }
}