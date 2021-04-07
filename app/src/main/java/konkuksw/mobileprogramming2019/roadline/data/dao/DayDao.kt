package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

@Dao
interface DayDao {
    @Query("SELECT * FROM day")
    fun getAll(): LiveData<List<Day>>

    @Query("SELECT * FROM day WHERE id = :dayId")
    suspend fun getDayById(dayId: Int): Day

    @Insert
    suspend fun insertAll(vararg days: Day)

    @Delete
    suspend fun delete(day: Day)

    @Update
    fun updateDay(day: Day)

    @Insert
    fun insert(day: Day)
}