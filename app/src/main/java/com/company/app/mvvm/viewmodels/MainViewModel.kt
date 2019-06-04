package com.company.app.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import com.company.app.api.ServiceAPI
import com.company.app.room.dao.UserDao
import com.company.app.room.entities.User
import com.company.app.utils.Logger
import com.company.app.utils.Utils
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val api: ServiceAPI, private val userDao: UserDao, private val utils: Utils) : BaseViewModel() {

    private val tokenError = MutableLiveData<Boolean>()


    init {
        checkUser()
    }

    private fun checkUser(){
        disposables.add(
            userDao.getUserSingle(0)
                .doOnError { tokenError.postValue(true) }
                .toFlowable()
                .onErrorResumeNext ( Flowable.just(User(0)) )
                .flatMap { userDao.getUserFlowable(0)}
                .doOnNext {user ->
                    if(user.token.isNullOrEmpty()){
                        setTokenError(true)
                    }else{
                        setTokenError(false)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { user -> run {
                        Logger.i("checkUser $user")
                    }},
                    { e -> run{
                        error.postValue(utils.processError(e))
                        Logger.i("checkUser error")
                    }}
                )
        )
    }

    fun setTokenError(isError:Boolean?){
        tokenError.postValue(isError)
    }
    fun tokenError() = tokenError
}