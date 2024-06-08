package com.dicoding.githubuser.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.dicoding.githubuser.data.local.SettingPreferences
import com.dicoding.githubuser.utils.Event
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(private val preference: SettingPreferences): ViewModel() {

    private var _themeSetting: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val themeSetting: LiveData<Event<Boolean>> = _themeSetting

    init {
        getThemeSetting()
    }

    private fun getThemeSetting() {
        viewModelScope.launch {
            val data = preference.getThemeSetting().first()
            _themeSetting.value = Event(data)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val preference: SettingPreferences) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SplashViewModel(preference) as T
        }
    }
}
