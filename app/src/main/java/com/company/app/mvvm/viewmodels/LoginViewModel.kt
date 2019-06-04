package com.company.app.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import com.company.app.api.ServiceAPI
import com.company.app.mvvm.models.LoginRequestSMSCode
import com.company.app.utils.Logger
import com.company.app.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val api: ServiceAPI, private val utils: Utils) : BaseViewModel(){

    private val getSMSSuccess =  MutableLiveData<Boolean>()
    val phoneNumber = MutableLiveData<CharSequence>()

    fun getSMSSuccess() = getSMSSuccess
    fun setSMSSuccess(isSuccess:Boolean?){
        getSMSSuccess.postValue(isSuccess)
    }

    fun phoneNumberListener(c:CharSequence){
        phoneNumber.postValue(c)
    }


    fun getSMSCode(){
        disposables.add(
            api.loginRequestSMSCode(LoginRequestSMSCode(phoneNumber.value.toString()))
                .doOnSubscribe { showLoader.postValue(true) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {showLoader.postValue(false)}
                .subscribe(
                    {
                        Logger.i("getSMSCode ok ")
                        setSMSSuccess(true)
                    },
                    { e -> run{
                        error.postValue(utils.processError(e))
                        Logger.i("getSMSCode error")
                        setSMSSuccess(false)
                    }}
                )
        )
    }
}