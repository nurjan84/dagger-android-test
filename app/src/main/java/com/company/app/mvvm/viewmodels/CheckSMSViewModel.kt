package com.company.app.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import com.company.app.api.ServiceAPI
import com.company.app.mvvm.models.LoginRequestSMSCode
import com.company.app.room.dao.UserDao
import com.company.app.room.entities.User
import com.company.app.utils.Logger
import com.company.app.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CheckSMSViewModel @Inject constructor(private val api: ServiceAPI, private val userDao: UserDao, private val utils: Utils) :BaseViewModel(){

    private val smsCode = MutableLiveData<CharSequence>()
    var phoneNumber:String? = null
    private val loginSuccess =  MutableLiveData<Boolean>()

    fun smsCodeListener(c:CharSequence){
        smsCode.postValue(c)
    }
    fun getSMSCode() = smsCode

    fun verifySMSCode(){
        if(smsCode.value == null || phoneNumber == null){return}
        disposables.add(
            api.loginVerifySMSCode(smsCode.value.toString(), LoginRequestSMSCode(phoneNumber!!))
                .doOnSubscribe { showLoader.postValue(true) }
                .doOnNext {
                    val user = User(0)
                    user.token = it.key
                    userDao.insert(user)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate {showLoader.postValue(false)}
                .subscribe(
                    {result -> run {
                        Logger.i("verifySMSCode ok $result ")
                        loginSuccess.postValue(true)
                    }},
                    { e -> run{
                        error.postValue(utils.processError(e))
                        Logger.i("verifySMSCode error $e")
                        loginSuccess.postValue(false)
                    }}
                )
        )
    }

    fun loginSuccess() = loginSuccess

}