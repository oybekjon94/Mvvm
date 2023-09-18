package com.example.mvvm_1.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_1.database.AppDatabase
import com.example.mvvm_1.database.entity.UserEntity
import com.example.mvvm_1.mapper.mapToEntity
import com.example.mvvm_1.models.UserData
import com.example.mvvm_1.networking.ApiServes
import com.example.mvvm_1.networking.ApiiClient
import com.example.mvvm_1.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch

//bu qism Viewmodel hisoblanadi
class UserViewModel(
    appDatabase: AppDatabase,
    apiService: ApiServes,
):ViewModel() {
    //olib kelish uchun repository kere boladi
    private val userRepository = UserRepository(apiService,appDatabase.userDao())
    //bu yerda resourceni xolati yoziladi yani loadingmi successmi
    //xozir bu yerda default xolatda Loading
    private val stateFlow = MutableStateFlow<Resource<List<UserEntity>>>(Resource.Loading())

    init {
        fetchUsers()
    }

    private fun fetchUsers(){
        viewModelScope.launch {
            userRepository.getUsers()
                .catch {
                    stateFlow.emit(Resource.Error(it))
                }.flatMapConcat {
                   val list = ArrayList<UserEntity>()
                    it.forEach{
                       list.add(it.mapToEntity(it))
                    }
                    userRepository.addUsers(list)
                }.collect{
                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))
                }
        }

    }
    //bu yerdan getter yaratib quyamiz
    fun getStateFlow():StateFlow<Resource<List<UserEntity>>>{
        return stateFlow
    }

}