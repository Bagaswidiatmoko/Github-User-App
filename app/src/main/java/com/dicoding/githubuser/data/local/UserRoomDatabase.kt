package com.dicoding.githubuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.githubuser.data.response.ResponseUserGithub


@Database(entities = [ResponseUserGithub.Item::class], version = 1, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}