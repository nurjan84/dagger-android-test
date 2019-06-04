package com.company.app.utils

import android.content.Context
import com.company.app.R
import com.company.app.mvvm.models.ErrorResponse
import com.google.gson.Gson
import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownServiceException

class Utils constructor( val context: Context) {

    fun processError(error:Throwable):Throwable{
        val exception = error.fillInStackTrace()
        return if(exception is HttpException){
            val code = exception.response().code()
            var errorBody = exception.response().errorBody()
            if (errorBody != null && code == 400 || code == 401){
                try {
                    val errorResponse = Gson().fromJson(errorBody!!.string(), ErrorResponse::class.java)
                    Throwable(errorResponse.errorDescr)
                }catch (e:Throwable){
                    e.printStackTrace()
                    Throwable("unexpected error")
                }
            }else{
                error
            }
        }else if (exception is SocketTimeoutException){
            Throwable(context.getString(R.string.socket_timeout_exception))
        }else if (exception is UnknownServiceException){
            error
        } else if (exception is CompositeException){
            var hasTimeOutException = false
            exception.exceptions.forEach {
                if(it is SocketTimeoutException){
                    hasTimeOutException = true
                }
            }
            if(hasTimeOutException){
                Throwable(context.getString(R.string.socket_timeout_exception))
            }else{
                error
            }
        }else{
            error
        }
    }

}