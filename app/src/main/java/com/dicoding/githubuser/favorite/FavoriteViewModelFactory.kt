package com.dicoding.githubuser.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.data.local.DatabaseHelper

class FavoriteViewModelFactory (private val db: DatabaseHelper) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
}