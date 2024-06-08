package com.dicoding.githubuser.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.githubuser.data.local.SettingPreferences
import kotlinx.coroutines.launch

class SettingViewModel (private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> = pref.getThemeSetting().asLiveData()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val pref: SettingPreferences): ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingViewModel(pref) as T
        }
    }
}