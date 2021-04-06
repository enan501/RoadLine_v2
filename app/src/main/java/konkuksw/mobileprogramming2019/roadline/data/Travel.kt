package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Travel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var title: String,
    var dateStart: LocalDate,
    var dateEnd: LocalDate,
    var img: String
)