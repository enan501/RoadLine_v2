package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Currency(
    @PrimaryKey val code: String,
    val name: String,
    val rate: Double,
    val symbol: String
)
