package com.example.mvvm_1.mapper

import com.example.mvvm_1.database.entity.UserEntity
import com.example.mvvm_1.models.UserData



fun UserData.mapToEntity(userData: UserData):UserEntity{

    return UserEntity(userData.id?: 0,userData.login?: "",userData.avatar_url?: "")
}