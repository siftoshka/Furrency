package az.siftoshka.furrency

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import az.siftoshka.furrency.di.prefModule
import com.airbnb.lottie.BuildConfig
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class App : Application() {

    private val sharedPreferences: SharedPreferences by inject()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        initKoin()
        initThemeMode()
    }

    private fun initKoin() = startKoin {
        androidContext(this@App)
        modules(prefModule)
    }

    private fun initThemeMode() {
        val themeID = sharedPreferences.getInt(Constants.PREF_MODE, 0)
        if (themeID == Constants.TURN_ON) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }

}