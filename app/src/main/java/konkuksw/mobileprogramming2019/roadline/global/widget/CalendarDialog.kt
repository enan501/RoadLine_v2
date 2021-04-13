package konkuksw.mobileprogramming2019.roadline.global.widget
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.model.OutDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.CalendarDialogLayoutBinding
import konkuksw.mobileprogramming2019.roadline.global.extension.getDrawableCompat
import konkuksw.mobileprogramming2019.roadline.global.extension.setTextColorRes
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.*

class CalendarDialog (context: Context) : Dialog(context) {

    val binding: CalendarDialogLayoutBinding by lazy{
        CalendarDialogLayoutBinding.inflate(LayoutInflater.from(context))
    }

    class Builder(val mContext: Context) {
        private var startDate: LocalDate? = null
        private var endDate: LocalDate? = null

        fun setStartDate(date: LocalDate) {
            startDate = date
        }

        fun setEndDate(date: LocalDate) {
            endDate = date
        }

        val dialog = CalendarDialog(mContext)
        fun create(): Builder {
            dialog.create()
            dialog.setContentView(dialog.binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            initCalendarView()
            dialog.binding.btnCancel.setOnClickListener { dialog.dismiss() }
            return this
        }

        private fun initCalendarView(){
            val startBackground: GradientDrawable by lazy {
                mContext.getDrawableCompat(R.drawable.calendar_day_picked_start) as GradientDrawable
            }

            val endBackground: GradientDrawable by lazy {
                mContext.getDrawableCompat(R.drawable.calendar_day_picked_end) as GradientDrawable
            }

            val dateFormat = DateTimeFormatter.ofPattern("yyyy년 MM월")
            class DayViewContainer(view: View) : ViewContainer(view) {
                val textView = view.findViewById<TextView>(R.id.calendarDayText)
                val roundView = view.findViewById<View>(R.id.roundView)
                lateinit var day: CalendarDay
                init{
                    view.setOnClickListener {
                        if (day.owner == DayOwner.THIS_MONTH){
                            val date = day.date
                            if (startDate != null) {
                                if (date < startDate || endDate != null) {
                                    startDate = date
                                    endDate = null
                                } else if (date != startDate) {
                                    endDate = date
                                }
                            } else {
                                startDate = date
                            }
                            dialog.binding.calendarView.notifyCalendarChanged()
                        }
                    }
                }
            }

            dialog.binding.calendarView.inDateStyle = InDateStyle.ALL_MONTHS
            dialog.binding.calendarView.outDateStyle = OutDateStyle.END_OF_ROW
            dialog.binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
                override fun bind(container: DayViewContainer, day: CalendarDay) {
                    container.day = day
                    container.textView.text = day.date.dayOfMonth.toString()
                    container.textView.background = null
                    container.roundView.visibility = View.INVISIBLE
                    if (day.owner == DayOwner.THIS_MONTH) {
                        when {
                            startDate == day.date && endDate == null -> {
                                container.textView.setTextColorRes(R.color.white)
                                container.roundView.visibility = View.VISIBLE
                                container.roundView.setBackgroundResource(R.drawable.calendar_day_picked_one)
                            }
                            day.date == startDate -> {
                                container.textView.setTextColorRes(R.color.white)
                                container.textView.background = startBackground
                            }
                            startDate != null && endDate != null && (day.date > startDate && day.date < endDate) -> {
                                container.textView.setTextColorRes(R.color.white)
                                container.textView.setBackgroundResource(R.drawable.calendar_day_picked_middle)
                            }
                            day.date == endDate -> {
                                container.textView.setTextColorRes(R.color.white)
                                container.textView.background = endBackground
                            }
                            else -> {
                                container.textView.setTextColorRes(R.color.darkGray)
                            }
                        }
                    }
                    else{
                        val startDate = startDate
                        val endDate = endDate
                        if (startDate != null && endDate != null) {
                            if ((day.owner == DayOwner.PREVIOUS_MONTH &&
                                        startDate.monthValue == day.date.monthValue &&
                                        endDate.monthValue != day.date.monthValue) ||
                                (day.owner == DayOwner.NEXT_MONTH &&
                                        startDate.monthValue != day.date.monthValue &&
                                        endDate.monthValue == day.date.monthValue) ||

                                (startDate < day.date && endDate > day.date &&
                                        startDate.monthValue != day.date.monthValue &&
                                        endDate.monthValue != day.date.monthValue)
                            ) {
                                container.textView.setTextColorRes(R.color.white)
                                container.textView.setBackgroundResource(R.drawable.calendar_day_picked_middle)
                            }
                        }
                    }
                }
                override fun create(view: View) = DayViewContainer(view)
            }

            //캘린더 날짜 속성 초기화
            val currentMonth = YearMonth.now()
            val firstMonth = currentMonth.minusMonths(10)
            val lastMonth = currentMonth.plusMonths(10)
            val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
            dialog.binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
            dialog.binding.calendarView.scrollToMonth(currentMonth)
            dialog.binding.calendarView.monthScrollListener = {
                dialog.binding.calendarMonthText.text = it.yearMonth.format(dateFormat)
            }

            dialog.binding.leftButton.setOnClickListener {
               dialog.binding.calendarView.findFirstVisibleMonth()?.let {
                    dialog.binding.calendarView.smoothScrollToMonth(it.yearMonth.minusMonths(1))
                }
            }

            dialog.binding.rightButton.setOnClickListener {
                dialog.binding.calendarView.findFirstVisibleMonth()?.let {
                    dialog.binding.calendarView.smoothScrollToMonth(it.yearMonth.plusMonths(1))
                }
            }
        }


        fun setButtonOk(onOkClick: View.OnClickListener) {
            dialog.binding.btnOk.setOnClickListener(onOkClick)
        }

        fun dismissDialog() {
            dialog.dismiss()
        }

        fun show(): CalendarDialog {
            dialog.show()
            return dialog
        }

        fun getStartDate(): LocalDate? {
            return startDate
        }

        fun getEndDate(): LocalDate? {
            return endDate
        }

    }
}