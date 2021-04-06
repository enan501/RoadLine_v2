package konkuksw.mobileprogramming2019.roadline.global

import android.app.Application
import androidx.room.Room
import konkuksw.mobileprogramming2019.roadline.data.AppDatabase

class MyApplication: Application() {
    companion object{
        lateinit var db: AppDatabase
    }


    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }


}