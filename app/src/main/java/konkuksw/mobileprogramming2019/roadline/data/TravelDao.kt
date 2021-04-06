package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TravelDao {
    @Query("SELECT * FROM travel")
    fun getAll(): List<Travel>

    @Query("SELECT * FROM travel WHERE id IN (:travelIds)")
    fun loadAllByIds(travelIds: IntArray): List<Travel>

    @Insert
    fun insertAll(vararg travels: Travel)

    @Delete
    fun delete(travel: Travel)

}