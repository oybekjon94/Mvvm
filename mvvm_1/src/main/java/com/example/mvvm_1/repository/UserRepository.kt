package com.example.mvvm_1.repository

import com.example.mvvm_1.database.dao.UserDao
import com.example.mvvm_1.database.entity.UserEntity
import com.example.mvvm_1.networking.ApiServes
import kotlinx.coroutines.flow.flow


//bu qism model hisoblanadi
class UserRepository(private val apiServes: ApiServes,private val userDao:UserDao) {

    fun getUsers() = apiServes.getUsers()

    fun addUsers(list:List<UserEntity>) = flow { emit((userDao.addUsers(list))) }

    fun getDatabaseUsers() = userDao.getUsers()

}