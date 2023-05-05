package id.ilhamelmujib.submissionandroidfundamental.ui.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.ilhamelmujib.submissionandroidfundamental.di.Injection


class SettingsViewModelFactory private constructor(
    private val preferences: SettingPreferences
): ViewModelProvider.NewInstanceFactory(){


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)){
            return  SettingsViewModel(preferences) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: SettingsViewModelFactory? = null

        fun getInstance(context: Context): SettingsViewModelFactory = instance ?: synchronized(this) {
            instance ?: SettingsViewModelFactory(Injection.providePreferences(context))
        }.also { instance = it }
    }

}