package konkuksw.mobileprogramming2019.roadline.data.relation

import androidx.lifecycle.LiveData
import androidx.room.Embedded
import androidx.room.Relation
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

data class TravelWithDaysAndPlans(
    @Embedded val travel: Travel,
    @Relation(
            entity =Day::class,
              parentColumn = "id",
              entityColumn = "travelId"
        )
        val daysWithPlans: List<DayWithPlans>)