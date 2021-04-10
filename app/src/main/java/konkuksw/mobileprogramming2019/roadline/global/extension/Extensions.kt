package konkuksw.mobileprogramming2019.roadline.global.extension

import android.content.Context
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun daysBetween(startDay: LocalDate, endDay: LocalDate): Int {
    return startDay.until(endDay).days
}

internal fun TextView.setTextColorRes(@ColorRes color: Int) = setTextColor(context.getColorCompat(color))
internal fun Context.getDrawableCompat(@DrawableRes drawable: Int) = ContextCompat.getDrawable(this, drawable)
internal fun Context.getColorCompat(@ColorRes color: Int) = ContextCompat.getColor(this, color)