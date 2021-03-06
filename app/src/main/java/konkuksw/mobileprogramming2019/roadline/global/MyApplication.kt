package konkuksw.mobileprogramming2019.roadline.global

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import konkuksw.mobileprogramming2019.roadline.data.AppDatabase
import konkuksw.mobileprogramming2019.roadline.data.repository.CurrencyRepo
import konkuksw.mobileprogramming2019.roadline.data.repository.DayRepo
import konkuksw.mobileprogramming2019.roadline.data.repository.PlanRepo
import konkuksw.mobileprogramming2019.roadline.data.repository.TravelRepo


class MyApplication: Application() {
    companion object{
        var db: AppDatabase? = null
        lateinit var travelRepo: TravelRepo
        lateinit var dayRepo: DayRepo
        lateinit var currencyRepo: CurrencyRepo
        lateinit var planRepo: PlanRepo

    }


    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getInstance(applicationContext)
        travelRepo = TravelRepo(this)
        dayRepo = DayRepo(this)
        planRepo = PlanRepo(this)
    }


}