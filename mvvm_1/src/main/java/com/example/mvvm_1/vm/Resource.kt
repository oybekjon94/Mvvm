package com.example.mvvm_1.vm

//bu yerda resourceni xolati yoziladi yani loadingmi successmi
//istalgan tip uchun generic qilamiz
sealed class Resource<T> {

    class Loading<T>:Resource<T>()
    class Success<T:Any>(val data:T):Resource<T>()
    class Error<T:Any>(val e:Throwable):Resource<T>()
}