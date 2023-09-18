package com.example.mvvm_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_1.database.AppDatabase
import com.example.mvvm_1.databinding.ActivityMainBinding
import com.example.mvvm_1.networking.ApiServes
import com.example.mvvm_1.networking.ApiiClient
import com.example.mvvm_1.vm.Resource
import com.example.mvvm_1.vm.UserViewModel
import com.example.mvvm_1.vm.UserViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


//bu qism View
class MainActivity : AppCompatActivity(),CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    //endi bu yerda ViewModel chaqirish kere
    private lateinit var viewModel: UserViewModel
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            UserViewModelFactory(
                AppDatabase.getInstance(this), ApiiClient.apiService)
        ) [UserViewModel::class.java]

        launch {
            viewModel.getStateFlow()
                .collect{
                   when(it){
                       is Resource.Error -> {
                          Toast.makeText(this@MainActivity,it.e.message,Toast.LENGTH_SHORT).show()
                       }
                       is Resource.Loading -> {

                       }
                       is Resource.Success -> {

                           Log.d(TAG, "onCreate: ${it.data}")

                       }
                   }
                }
        }
        viewModel.getStateFlow()

    }

    override val coroutineContext: CoroutineContext
    //asosiy oqim main bolishi uchun
        get() = Dispatchers.Main
}