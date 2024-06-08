package com.dicoding.githubuser.data.local

import android.content.Context
import androidx.room.Room

class DatabaseHelper(private val context: Context) {
    private val db = Room.databaseBuilder(context, UserRoomDatabase::class.java, "usergithub.db")
        .allowMainThreadQueries()
        .build()

    val userDao = db.userDao()
}