package konkuksw.mobileprogramming2019.roadline.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import konkuksw.mobileprogramming2019.roadline.data.dao.*
import konkuksw.mobileprogramming2019.roadline.data.entity.*

@Database(entities = [Currency::class, Day::class, Money::class, Photo::class, Plan::class, Travel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun dayDao(): DayDao
    abstract fun moneyDao(): MoneyDao
    abstract fun photoDao(): PhotoDao
    abstract fun planDao(): PlanDao
    abstract fun travelDao(): TravelDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            return INSTANCE ?: synchronized(AppDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "roadline-db").build().also { INSTANCE = it }
            }
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}