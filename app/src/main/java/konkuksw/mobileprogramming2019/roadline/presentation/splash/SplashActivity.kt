package konkuksw.mobileprogramming2019.roadline.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import konkuksw.mobileprogramming2019.roadline.R
import konkuksw.mobileprogramming2019.roadline.databinding.ActivitySplashBinding
import konkuksw.mobileprogramming2019.roadline.presentation.base.BaseActivity
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListActivity
import konkuksw.mobileprogramming2019.roadline.presentation.travelList.TravelListViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding>(
    R.layout.activity_splash
) {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, TravelListActivity::class.java))
        finish()
    }
}