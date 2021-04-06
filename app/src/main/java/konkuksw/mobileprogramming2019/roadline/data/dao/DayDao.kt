package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import konkuksw.mobileprogramming2019.roadline.data.entity.Day

@Dao
interface DayDao {
    @Query("SELECT * FROM day")
    fun getAll(): List<Day>

    @Query("SELECT * FROM day WHERE id IN (:dayIds)")
    fun loadAllByIds(dayIds: IntArray): List<Day>

    @Insert
    fun insertAll(vararg days: Day)

    @Delete
    fun delete(day: Day)
}