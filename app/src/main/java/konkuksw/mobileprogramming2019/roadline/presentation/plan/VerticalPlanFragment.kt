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
import androidx.recyclerview.widget.ItemTouchHelper
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.databinding.FragmentVerticalPlanBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseFragment


class VerticalPlanFragment : BaseFragment<FragmentVerticalPlanBinding>(
    R.layout.fragment_vertical_plan
) {
    val planViewModel: PlanViewModel by activityViewModels()
    private lateinit var callback: DateItemTouchHelperCallback
    private lateinit var listAdapter: VerticalPlanListAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = planViewModel
        listAdapter = VerticalPlanListAdapter(planViewModel, requireActivity(),
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

                override fun onItemDrag(plan: Plan, viewHolder: VerticalPlanListAdapter.PlanViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }

                override fun onItemDelete(plan: Plan) {
                    planViewModel.deletePlan(plan)

                }
            })
        callback = DateItemTouchHelperCallback(listAdapter)
        binding.rvVerticalPlan.adapter = listAdapter
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvVerticalPlan)

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