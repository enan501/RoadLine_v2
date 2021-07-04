package konkuksw.mobileprogramming2019.roadline.presentation.plan

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

interface ItemTouchHelperListener {
    fun onItemMove(fromPos: Int, toPos: Int): Boolean
}

class DateItemTouchHelperCallback(private val adapter: VerticalPlanListAdapter) :ItemTouchHelper.Callback(){

//    private val POSITION_UNKNOWN = -1
//    private var originPosition = POSITION_UNKNOWN
//    private var oldPosition = POSITION_UNKNOWN
//    private var newPosition = POSITION_UNKNOWN
//    var dateListAdapter = adapter
//    var context = context

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
//        Log.d("mytag", p1.bindingAdapterPosition.toString() + p2.bindingAdapterPosition.toString())
        adapter.onItemMove(p1.bindingAdapterPosition, p2.bindingAdapterPosition)
        return false
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
//        if(newPosition == dateListAdapter.itemCount-1) newPosition--
//        dateListAdapter.onAttachedToRecyclerView(recyclerView)
//        dateListAdapter.moveItem(originPosition, newPosition)
//        oldPosition = POSITION_UNKNOWN;
//        newPosition = POSITION_UNKNOWN;
//        originPosition = POSITION_UNKNOWN;
        if (recyclerView.adapter is VerticalPlanListAdapter) {
            (recyclerView.adapter as VerticalPlanListAdapter).syncPosition()
        }
        super.clearView(recyclerView, viewHolder)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}