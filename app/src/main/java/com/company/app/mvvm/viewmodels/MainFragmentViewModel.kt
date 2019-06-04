package com.company.app.mvvm.viewmodels

import com.company.app.api.ServiceAPI
import com.company.app.room.dao.UserDao
import com.company.app.utils.Utils
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(private val api: ServiceAPI, private val userDao: UserDao, private val utils: Utils) : BaseViewModel(){

}