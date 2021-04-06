package konkuksw.mobileprogramming2019.roadline

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import konkuksw.mobileprogramming2019.roadline.data.Travel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        GlobalScope.launch(Dispatchers.IO) {
//            MyApplication.db.travelDao().insertAll(Travel(0, "hi", LocalDate.now(), LocalDate.now(), "hihi"))
//            var travels = MyApplication.db.travelDao().getAll()
//            Log.d("mytag", travels.toString())
//        }
    }
}