package konkuksw.mobileprogramming2019.roadline

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import konkuksw.mobileprogramming2019.roadline.data.AppDatabase

class MyApplication: Application() {
    companion object{
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        connectDB()
    }

    private fun connectDB(){
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }
}