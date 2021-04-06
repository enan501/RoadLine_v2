package konkuksw.mobileprogramming2019.roadline

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import konkuksw.mobileprogramming2019.roadline.data.AppDatabase
import konkuksw.mobileprogramming2019.roadline.data.TravelRepo

class MyApplication: Application() {
    companion object{
        var db: AppDatabase? = null
        lateinit var travelRepo: TravelRepo
    }


    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getInstance(applicationContext)
        travelRepo = TravelRepo(this)
    }


}