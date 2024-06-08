package com.dicoding.githubuser.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.githubuser.data.local.DatabaseHelper

class DetailViewModelFactory (private val db: DatabaseHelper) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DetailViewModel(db) as T
}