package com.example.mvvm_1.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_1.database.AppDatabase
import com.example.mvvm_1.networking.ApiServes
import java.lang.Exception

class UserViewModelFactory(
    private val appDatabase: AppDatabase,
    private val apiServes: ApiServes,

):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(appDatabase,apiServes)as T
        }
        return throw Exception("Error")
    }
}