package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlanDao {
    @Query("SELECT * FROM plan")
    fun getAll(): List<Plan>

    @Query("SELECT * FROM plan WHERE id IN (:planIds)")
    fun loadAllByIds(planIds: IntArray): List<Plan>

    @Insert
    fun insertAll(vararg plans: Plan)

    @Delete
    fun delete(plan: Plan)
}