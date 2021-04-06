package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Day::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("dayId"))
])
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val dayId: Int,
    var name: String,
    var nameAlter: String? = null,
    var locationX: Double,
    var locationY: Double,
    var time: Long? = null,
    var memo: String? = null,
    var pos: Int = 0
)