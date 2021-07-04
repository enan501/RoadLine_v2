package konkuksw.mobileprogramming2019.roadline.global.util

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class GridLayoutManagerWrapper(context: Context, spanCount:Int): GridLayoutManager(context, spanCount) {
    override fun supportsPredictiveItemAnimations(): Boolean {
        return false
    }
}