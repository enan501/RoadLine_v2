package konkuksw.mobileprogramming2019.roadline.global.extension

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun daysBetween(startDay: LocalDate, endDay: LocalDate): Int {
    return startDay.until(endDay).days
}