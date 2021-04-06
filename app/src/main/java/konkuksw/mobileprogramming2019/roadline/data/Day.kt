package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.ColumnInfo
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
    @PrimaryKey(autoGenerate = true) val id: Int,
    val travelId: Int,
    val dayNum: Int,
    var date: LocalDate,
    var img: String
)