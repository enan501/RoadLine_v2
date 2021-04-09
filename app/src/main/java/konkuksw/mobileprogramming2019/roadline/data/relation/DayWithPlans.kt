package konkuksw.mobileprogramming2019.roadline.data.relation

import androidx.room.Embedded
import androidx.room.Relation
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

data class DayWithPlans (
    @Embedded val day: Day,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayId"
    )
    val plans: List<Plan>)