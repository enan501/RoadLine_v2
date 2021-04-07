package konkuksw.mobileprogramming2019.roadline.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

data class TravelWithDays(
    @Embedded val travel: Travel,
    @Relation(
              parentColumn = "id",
              entityColumn = "travelId"
        )
        val days: List<Day>)