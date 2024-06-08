package com.dicoding.githubuser.ui

import android.util.Log
import androidx.lifecycle.*
import com.dicoding.githubuser.data.local.SettingPreferences
import com.dicoding.githubuser.data.retrofit.ApiConfig
import com.dicoding.githubuser.utils.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val resultUser = MutableLiveData<Result>()

    fun getUser() {
        viewModelScope.launch {
            flow {
                val response = ApiConfig
                    .githubService
                    .getUserGithub()

                emit(response)
            }.onStart {
                resultUser.value = Result.Loading(true)
            }.onCompletion {
                resultUser.value = Result.Loading(false)
            }.catch {
                it.printStackTrace()
                resultUser.value = Result.Error(it)
            }.collect {
                resultUser.value = Result.Success(it)
            }
        }
    }

    fun getUser(username: String) {
        viewModelScope.launch {
            flow {
                val response = ApiConfig
                    .githubService
                    .searchUserGithub(
                        mapOf(
                            "q" to username,
                            "per_page" to 10
                        )
                    )

                emit(response)
            }.onStart {
                resultUser.value = Result.Loading(true)
            }.onCompletion {
                resultUser.value = Result.Loading(false)
            }.catch {
                it.printStackTrace()
                resultUser.value = Result.Error(it)
            }.collect {
                resultUser.value = Result.Success(it.items)
            }
        }
    }
}