package konkuksw.mobileprogramming2019.roadline.data.relation

import androidx.room.Entity
import androidx.room.ForeignKey
import konkuksw.mobileprogramming2019.roadline.data.entity.Currency
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

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