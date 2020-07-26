package az.siftoshka.furrency.di

import android.content.Context
import az.siftoshka.furrency.Constants.SETTINGS_PREFERENCES
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val prefModule = module {
    single {
        androidApplication().getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)
    }
}
