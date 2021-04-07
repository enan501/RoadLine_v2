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

    @Query("SELECT * FROM currency WHERE code IN (:currencyIds)")
    fun loadAllByIds(currencyIds: IntArray): List<Currency>

    @Insert
    fun insertAll(vararg currencys: Currency)

    @Delete
    fun delete(currency: Currency)
}