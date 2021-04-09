package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDaysAndPlans

@Dao
interface TravelDao {
    @Query("SELECT * FROM travel")
    fun getAll(): LiveData<List<Travel>>

    @Query("SELECT * FROM travel WHERE id = :travelId")
    suspend fun getTravelById(travelId: Int): Travel

    @Insert
    suspend fun insert(travel: Travel): Long

    @Delete
    suspend fun delete(travel: Travel)

    @Update
    suspend fun updateTravel(travel: Travel)

    @Transaction
    @Query("SELECT * FROM travel WHERE id = :travelId")
    fun getTravelWithDays(travelId: Int): LiveData<TravelWithDays>

    @Transaction
    @Query("SELECT * FROM travel WHERE id = :travelId")
    fun getTravelWithDaysAndPlans(travelId: Int): LiveData<TravelWithDaysAndPlans>

}