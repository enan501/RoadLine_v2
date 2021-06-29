package konkuksw.mobileprogramming2019.roadline.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan

@Dao
interface PlanDao {
    @Query("SELECT * FROM `plan`")
    fun getAll(): LiveData<List<Plan>>

    @Query("SELECT * FROM `plan` WHERE id IN (:planIds)")
    fun loadAllByIds(planIds: IntArray): List<Plan>

    @Query("SELECT * FROM `plan` WHERE id = :planId")
    suspend fun getPlanById(planId: Int): Plan

    @Update
    fun updatePlan(day: Plan)

    @Insert
    fun insert(plan: Plan)

    @Delete
    fun delete(plan: Plan)

}