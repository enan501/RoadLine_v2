package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import konkuksw.mobileprogramming2019.roadline.data.entity.Currency

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    fun getAll(): List<Currency>


    @Query("SELECT * FROM currency WHERE code = (:currencyCode) LIMIT 1")
    fun getCurrencyByCode(currencyCode: String): Currency

    @Insert
    fun insertAll(currencys: List<Currency>)

    @Delete
    fun delete(currency: Currency)
}