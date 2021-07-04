package konkuksw.mobileprogramming2019.roadline.global.util

import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan

object ViewBindingAdapter {
    @BindingAdapter("setSelectedDayNum")
    @JvmStatic
    fun TextView.setSelectedDayNum(selectedDay: Int?){
        this.isSelected = (this.text == selectedDay.toString()) || (selectedDay == 0 && this.text == "A")
    }

    @BindingAdapter("setVisible")
    @JvmStatic
    fun View.setVisible(setVisible: Boolean){
        this.visibility = if(setVisible) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("setTouchListener")
    @JvmStatic
    fun View.setTouchListener(onTouch: ()-> Unit) {
        this.setOnTouchListener{ _, event->
            if(event.action == MotionEvent.ACTION_DOWN) {
                Log.d("mytag", "action down")
                onTouch()
            }
            else if(event.action == MotionEvent.ACTION_UP) {
                Log.d("mytag", "action up")
            }
            performClick()
            false
        }
    }


}