package konkuksw.mobileprogramming2019.roadline.global.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

object ViewBindingAdapter {
    @BindingAdapter("setSelectedDayNum")
    @JvmStatic
    fun TextView.setSelectedDayNum(selectedDay: Int?){
        this.isSelected = (this.text == selectedDay.toString()) || (selectedDay == 0 && this.text == "A")
    }

    @BindingAdapter("setVisible")
    @JvmStatic
    fun View.setVisible(setVisible:Boolean){
        this.visibility = if(setVisible) View.VISIBLE else View.INVISIBLE
    }

    @BindingAdapter("direction","position","itemCount")
    @JvmStatic
    fun View.setVisibleByPosition(direction:Int, position:Int, itemCount:Int){ //direction - 0,1,2,3 (left, top, right, bottom)
        when(direction){
            0 ->{
                this.setVisible(position%10 != 0 && position%10 != 9 && !(position == itemCount-1 && position%10 >=5 && position%10 <= 9))
            }
            1 ->{
                this.setVisible((position%10 == 0 && position != 0) || position%10 == 5)
            }
            2 ->{
                this.setVisible(position%10 != 4 && position%10 != 5 && !(position == itemCount-1 && position%10 <= 3))
            }
            3 ->{
                this.setVisible((position%10 == 9 || position%10 == 4) && !((position == itemCount-1) && (position%10 == 4 || position%10 == 9)))
            }
        }
    }
}