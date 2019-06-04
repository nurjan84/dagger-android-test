package com.company.app.mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.company.app.utils.Logger
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel: ViewModel(){
    val disposables = CompositeDisposable()
    val error = MutableLiveData<Throwable>()
    val showLoader =  MutableLiveData<Boolean>()


    override fun onCleared() {
        super.onCleared()
        Logger.i("BaseViewModel onCleared ")
        disposables.clear()
    }

    fun error(): LiveData<Throwable> {
        return error
    }

    fun loader(): LiveData<Boolean> {
        return showLoader
    }
}