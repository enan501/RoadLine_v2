package konkuksw.mobileprogramming2019.roadline.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Travel(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var title: String,
    var dateStart: LocalDate,
    var dateEnd: LocalDate,
    var img: String?= null
)