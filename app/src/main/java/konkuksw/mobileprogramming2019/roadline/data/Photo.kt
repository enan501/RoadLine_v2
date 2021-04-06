package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(
    entity = Day::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("dayId"))
])
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dayId: Int,
    var img: String,
    var dateTime: LocalDateTime
)