package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import konkuksw.mobileprogramming2019.roadline.data.entity.Money

@Dao
interface MoneyDao {
    @Query("SELECT * FROM money")
    fun getAll(): List<Money>

    @Query("SELECT * FROM money WHERE id IN (:moneyIds)")
    fun loadAllByIds(moneyIds: IntArray): List<Money>

    @Insert
    fun insertAll(vararg moneys: Money)

    @Delete
    fun delete(money: Money)
}