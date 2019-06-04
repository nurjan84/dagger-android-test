package com.company.app.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.app.room.dao.UserDao
import com.company.app.room.entities.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase(){
    abstract fun userDao(): UserDao

}