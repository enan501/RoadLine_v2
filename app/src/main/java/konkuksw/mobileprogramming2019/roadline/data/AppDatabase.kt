package konkuksw.mobileprogramming2019.roadline.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Currency::class, Day::class, Money::class, Photo::class, Plan::class, Travel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
    abstract fun dayDao(): DayDao
    abstract fun moneyDao(): MoneyDao
    abstract fun photoDao(): PhotoDao
    abstract fun planDao(): PlanDao
    abstract fun travelDao(): TravelDao
}