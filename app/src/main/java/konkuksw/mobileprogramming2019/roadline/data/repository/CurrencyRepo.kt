package konkuksw.mobileprogramming2019.roadline.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import konkuksw.mobileprogramming2019.roadline.data.dao.CurrencyDao
import konkuksw.mobileprogramming2019.roadline.data.dao.DayDao
import konkuksw.mobileprogramming2019.roadline.data.entity.Currency
import konkuksw.mobileprogramming2019.roadline.data.entity.Day
import konkuksw.mobileprogramming2019.roadline.global.MyApplication

class CurrencyRepo(application: Application) {
    private val currencyDao: CurrencyDao
        get() = MyApplication.db!!.currencyDao()

    suspend fun getAll(): List<Currency> {
        return currencyDao.getAll()
    }

    suspend fun insertAll(currencys: List<Currency>) {
        currencyDao.insertAll(currencys)
    }

    suspend fun getCurrencyByCode(currencyCode: String): Currency {
        return currencyDao.getCurrencyByCode(currencyCode)
    }


}