package konkuksw.mobileprogramming2019.roadline.global.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {
    @BindingAdapter("setSelectedDayNum")
    @JvmStatic
    fun TextView.setSelectedDayNum(selectedDay: Int?){
        this.isSelected = (this.text == selectedDay.toString()) || (selectedDay == null && this.text == "A")
    }
}