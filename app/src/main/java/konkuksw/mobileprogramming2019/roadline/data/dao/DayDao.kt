package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays

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

    @Transaction
    @Query("SELECT * FROM day WHERE id = :dayId")
    fun getDayWithPlans(dayId: Int): LiveData<DayWithPlans>

    @Transaction
    @Query("SELECT * FROM day")
    fun getAllDayWithPlans(): LiveData<DayWithPlans>

}