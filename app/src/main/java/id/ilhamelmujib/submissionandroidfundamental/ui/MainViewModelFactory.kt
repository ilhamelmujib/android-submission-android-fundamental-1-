package id.ilhamelmujib.submissionandroidfundamental.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.di.Injection
import id.ilhamelmujib.submissionandroidfundamental.ui.settings.SettingPreferences


class MainViewModelFactory private constructor(
    private val preferences: SettingPreferences
): ViewModelProvider.NewInstanceFactory(){


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(preferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: MainViewModelFactory? = null

        fun getInstance(context: Context): MainViewModelFactory = instance ?: synchronized(this) {
            instance ?: MainViewModelFactory(Injection.providePreferences(context))
        }.also { instance = it }
    }

}