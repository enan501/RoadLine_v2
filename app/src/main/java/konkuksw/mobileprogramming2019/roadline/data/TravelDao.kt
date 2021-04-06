package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.*

@Dao
interface TravelDao {
    @Query("SELECT * FROM travel")
    fun getAll(): List<Travel>

    @Query("SELECT * FROM travel WHERE id = :travelId")
    fun getTravelById(travelId: Int): Travel

    @Insert
    fun insert(travel: Travel)

    @Delete
    fun delete(travel: Travel)

    @Update
    fun updateTravel(travel: Travel)


}