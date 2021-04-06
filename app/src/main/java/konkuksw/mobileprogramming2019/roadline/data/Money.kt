package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.*
import java.time.LocalDateTime

@Entity(foreignKeys = [ForeignKey(
    entity = Day::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("dayId"),
    onDelete = ForeignKey.CASCADE)
])
data class Money(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dayId: Int,
    var img: String,
    var price: Double,
    var category: String,
    var dateTime: LocalDateTime,
    var memo: String?,
    @Embedded var currency: Currency
)