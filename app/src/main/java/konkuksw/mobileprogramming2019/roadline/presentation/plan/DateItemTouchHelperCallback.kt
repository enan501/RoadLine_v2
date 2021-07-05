package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelperListener {
    fun onItemMove(fromPos: Int, toPos: Int): Boolean
}

class DateItemTouchHelperCallback(private val adapter: VerticalPlanListAdapter) :ItemTouchHelper.Callback(){

    private var startPos = -1
    private var endPos = -1
    private var isStart = true

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
        adapter.onItemMove(p1.bindingAdapterPosition, p2.bindingAdapterPosition)
        if(isStart){
            startPos = p1.bindingAdapterPosition
            isStart = false
        }
        endPos = p2.bindingAdapterPosition
        return false
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if(startPos != endPos) {
            if (recyclerView.adapter is VerticalPlanListAdapter) {
                (recyclerView.adapter as VerticalPlanListAdapter).updatePosition(startPos, endPos)
            }
        }
        isStart = true
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}