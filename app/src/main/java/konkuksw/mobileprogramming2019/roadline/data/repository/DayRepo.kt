package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import konkuksw.mobileprogramming2019.roadline.data.dao.DayDao
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.global.MyApplication

class DayRepo(application: Application) {
    private val dayDao: DayDao
    get() = MyApplication.db!!.dayDao()

    suspend fun getAll(): LiveData<List<Day>> {
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
}