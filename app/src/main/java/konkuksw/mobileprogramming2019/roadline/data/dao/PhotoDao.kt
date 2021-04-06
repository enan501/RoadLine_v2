package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import konkuksw.mobileprogramming2019.roadline.data.entity.Photo

@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo")
    fun getAll(): List<Photo>

    @Query("SELECT * FROM photo WHERE id IN (:photoIds)")
    fun loadAllByIds(photoIds: IntArray): List<Photo>

    @Insert
    fun insertAll(vararg photos: Photo)

    @Delete
    fun delete(photo: Photo)
}