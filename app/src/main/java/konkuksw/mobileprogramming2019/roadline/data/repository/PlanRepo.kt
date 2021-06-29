package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
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