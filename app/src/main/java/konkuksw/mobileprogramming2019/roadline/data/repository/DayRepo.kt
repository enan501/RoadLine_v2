package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import konkuksw.mobileprogramming2019.roadline.data.dao.DayDao
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.relation.DayWithPlans
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.global.MyApplication

class DayRepo(application: Application) {
    private val dayDao: DayDao
    get() = MyApplication.db!!.dayDao()

    fun getAll(): LiveData<List<Day>> {
        return dayDao.getAll()
    }

    suspend fun getDayById(dayId: Int): Day {
        return dayDao.getDayById(dayId)
    }

    suspend fun updateDay(day: Day) {
        dayDao.updateDay(day)
    }

    suspend fun insert(day: Day) {
        return dayDao.insert(day)
    }

    suspend fun delete(day: Day) {
        return dayDao.delete(day)
    }

    fun getDayWithPlans(dayId: Int): LiveData<DayWithPlans> {
        if (dayId != -1) {
            return dayDao.getDayWithPlans(dayId)
        }
        else{
            return dayDao.getAllDayWithPlans()
        }
    }
}