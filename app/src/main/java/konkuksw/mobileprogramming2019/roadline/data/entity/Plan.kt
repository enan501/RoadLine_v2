package konkuksw.mobileprogramming2019.roadline.data.entity
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(foreignKeys = [ForeignKey(
    entity = Day::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("dayId"),
    onDelete = ForeignKey.CASCADE)
])
@Parcelize
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val dayId: Int,
    var name: String,
    var nameAlter: String? = null,
    var locationX: Double,
    var locationY: Double,
    var time: Int? = null,
    var memo: String? = null,
    var pos: Int = 0
): Parcelable