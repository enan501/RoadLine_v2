package konkuksw.mobileprogramming2019.roadline.global.widget

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.databinding.DialogTravelAddBinding
import konkuksw.mobileprogramming2019.roadline.global.extension.periodToString
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListViewModel
import java.time.LocalDate

class TravelAddDialog(context: Context): Dialog(context) {

    val binding: DialogTravelAddBinding by lazy{
        DialogTravelAddBinding.inflate(LayoutInflater.from(context))
    }

    class Builder(private val mContext: Context, private val viewModel: TravelListViewModel) {
        private val dialog = TravelAddDialog(mContext)
        private var dateStart: LocalDate? = null
        private var dateEnd: LocalDate? = null
        fun create(): Builder {
            dialog.create()
            dialog.setContentView(dialog.binding.root)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            var size = Point()
            dialog.window!!.windowManager.defaultDisplay.getSize(size)
            dialog.window!!.attributes.let{
                it.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                it.width = (size.x * 0.872f).toInt()
            }
            dialog.binding.btnClose.setOnClickListener { dialog.dismiss() }

            dialog.binding.btnOk.setOnClickListener {
                val title = dialog.binding.tvTravelTitle.text.toString()
                if (dateStart != null && dateEnd != null) {
                    viewModel.addTravel(Travel(title = title, dateStart = dateStart!!, dateEnd = dateEnd!!))
                }
                // 화폐 추가
                dismissDialog()
            }

            dialog.binding.tvDatePick.setOnClickListener {
                makeCalendarDialog()
            }

            return this
        }

        private fun makeCalendarDialog() {
            val calendarDialog = CalendarDialog.Builder(mContext).create()
            calendarDialog.setButtonOk{
                dateStart = calendarDialog.getStartDate()
                dateEnd = calendarDialog.getEndDate()
                calendarDialog.dismissDialog()
                if (dateStart != null && dateEnd != null) {
                    dialog.binding.tvDatePick.text = periodToString(dateStart!!, dateEnd!!)
                }
            }
            dateStart?.let { calendarDialog.setStartDate(dateStart!!) }
            dateEnd?.let { calendarDialog.setEndDate(dateEnd!!) }
            calendarDialog.show()
        }

        private fun setCurrencyAdapter(adapter: ArrayAdapter<String>): Builder {
            dialog.binding.spCurrency.adapter = adapter
            dialog.binding.spCurrency.setPositiveButton("취소")
            dialog.binding.spCurrency.setSelection(adapter.count)
            return this
        }

        private fun dismissDialog() {
            dialog.dismiss()
        }

        fun show(): TravelAddDialog {
            dialog.show()
            return dialog
        }
    }
}