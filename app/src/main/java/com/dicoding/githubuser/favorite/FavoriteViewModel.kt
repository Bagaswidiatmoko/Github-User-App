package com.dicoding.githubuser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.data.local.DatabaseHelper

class FavoriteViewModel(private val dbModule: DatabaseHelper) : ViewModel() {

    fun getUserFavorite() = dbModule.userDao.loadAll()
}