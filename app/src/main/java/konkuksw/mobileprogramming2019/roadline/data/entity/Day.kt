package konkuksw.mobileprogramming2019.roadline.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(foreignKeys = [ForeignKey(
    entity = Travel::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("travelId"),
    onDelete = ForeignKey.CASCADE)
])
data class Day(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val travelId: Int,
    var date: LocalDate,
    var img: String
)