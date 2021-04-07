package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel

@Dao
interface TravelDao {
    @Query("SELECT * FROM travel")
    fun getAll(): LiveData<List<Travel>>

    @Query("SELECT * FROM travel WHERE id = :travelId")
    fun getTravelById(travelId: Int): Travel

    @Insert
    fun insert(travel: Travel)

    @Delete
    fun delete(travel: Travel)

    @Update
    fun updateTravel(travel: Travel)


}