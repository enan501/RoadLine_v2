package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import konkuksw.mobileprogramming2019.roadline.data.entity.Travel
import konkuksw.mobileprogramming2019.roadline.data.dao.TravelDao
import konkuksw.mobileprogramming2019.roadline.global.MyApplication

class TravelRepo(application: Application) {
    private val travelDao: TravelDao by lazy {
        MyApplication.db!!.travelDao()
    }

    private val travels: List<Travel> by lazy {
        travelDao.getAll()
    }

    fun getAll(): List<Travel> {
        return travels
    }

    fun getTravelById(travelId: Int): Travel {
        return travelDao.getTravelById(travelId)
    }

    fun updateTravel(travel: Travel) {
        travelDao.updateTravel(travel)
    }

    fun insert(travel: Travel) {
        return travelDao.insert(travel)
    }

    fun delete(travel: Travel) {
        return travelDao.delete(travel)
    }
}