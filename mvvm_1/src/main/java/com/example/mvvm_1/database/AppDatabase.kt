package com.example.mvvm_1.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_1.database.dao.UserDao
import com.example.mvvm_1.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun userDao():UserDao

    companion object {
        private var INSTANCE : AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,AppDatabase::class.java,"my_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }


}