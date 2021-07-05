package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Transaction
import konkuksw.mobileprogramming2019.roadline.data.dao.DayDao
import konkuksw.mobileprogramming2019.roadline.data.dao.TravelDao
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDays
import konkuksw.mobileprogramming2019.roadline.data.relation.TravelWithDaysAndPlans
import konkuksw.mobileprogramming2019.roadline.global.MyApplication
import konkuksw.mobileprogramming2019.roadline.global.extension.daysBetween
import konkuksw.mobileprogramming2019.roadline.global.extension.totalMinToHour
import java.time.LocalDate

class TravelRepo(application: Application) {
    private val travelDao: TravelDao by lazy {
        MyApplication.db!!.travelDao()
    }

    private val dayDao: DayDao by lazy {
        MyApplication.db!!.dayDao()
    }

    private val travels: LiveData<List<Travel>> by lazy {
        travelDao.getAll()
    }

    fun getAll(): LiveData<List<Travel>> {
        return travels
    }

    suspend fun getTravelById(travelId: Int): Travel {
        return travelDao.getTravelById(travelId)
    }

    suspend fun updateTravel(travel: Travel) {
        travelDao.updateTravel(travel)

    }


    @Transaction
    suspend fun insertWithDays(travel: Travel) {
        val travelId = travelDao.insert(travel)
        val dayCount = daysBetween(travel.dateStart, travel.dateEnd)
        for (i in 0..dayCount){
            MyApplication.dayRepo.insert(Day(null, travelId.toInt(), travel.dateStart.plusDays(i.toLong()),""))
        }
    }

    suspend fun delete(travel: Travel) {
        return travelDao.delete(travel)
    }

    fun getTravelWithDays(travelId: Int): LiveData<TravelWithDays> {
        return travelDao.getTravelWithDays(travelId)
    }

    fun getTravelWithDaysAndPlans(travelId: Int): LiveData<TravelWithDaysAndPlans> {
        return travelDao.getTravelWithDaysAndPlans(travelId)
    }
}