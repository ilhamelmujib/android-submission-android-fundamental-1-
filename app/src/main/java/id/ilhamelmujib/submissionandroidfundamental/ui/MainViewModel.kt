package id.ilhamelmujib.submissionandroidfundamental.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.ilhamelmujib.submissionandroidfundamental.ui.settings.SettingPreferences

class MainViewModel(private val preferences: SettingPreferences) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> {
        return preferences.getThemeSetting().asLiveData()
    }

}