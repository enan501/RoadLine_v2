package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import konkuksw.mobileprogramming2019.roadline.data.dao.PlanDao
import konkuksw.mobileprogramming2019.roadline.data.entity.Plan
import konkuksw.mobileprogramming2019.roadline.global.MyApplication

class PlanRepo(application: Application) {
    private val planDao: PlanDao
        get() = MyApplication.db!!.planDao()

    fun getAll(): LiveData<List<Plan>> {
        return planDao.getAll()
    }

    suspend fun getPlanById(PlanId: Int): Plan {
        return planDao.getPlanById(PlanId)
    }

    suspend fun updatePlan(planId: Int, name: String, nameAlter: String?, locationX: Double, locationY: Double, time: Int?, memo: String?) {
        planDao.updatePlan(planId, name, nameAlter, locationX, locationY, time, memo)
    }

    private fun updatePlanPos(planId: Int, pos: Int) {
        planDao.updatePlanPos(planId, pos)
    }

    suspend fun updatePlanPosRange(list: List<Plan>, startPos: Int, endPos: Int) {
        for(i in startPos..endPos) {
            updatePlanPos(list[i].id!!, i)
        }
    }


    suspend fun updatePlan(Plan: Plan) {
        planDao.updatePlan(Plan)
    }

    suspend fun insert(Plan: Plan) {
        return planDao.insert(Plan)
    }

    suspend fun delete(Plan: Plan) {
        return planDao.delete(Plan)
    }


}