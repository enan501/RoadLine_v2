package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["playlistId","songId"],
    foreignKeys = [
        ForeignKey(entity = Travel::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("travelId")),
        ForeignKey(entity = Currency::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("currencyId"))
    ]
)
data class TravelCurrencyJoin(
    val travelId: Int,
    val currencyId: Int
)