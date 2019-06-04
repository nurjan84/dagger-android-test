package com.company.app.api

import com.company.app.mvvm.models.LoginRequestSMSCode
import com.company.app.mvvm.models.VerifySMSResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ServiceAPI {


    @POST("/app/login/sms")
    fun loginRequestSMSCode(@Body body: LoginRequestSMSCode) : Completable

    @POST("/app/login")
    fun loginVerifySMSCode(@Header("userRegistrationToken") code:String, @Body body: LoginRequestSMSCode) : Observable<VerifySMSResponse>

    @GET("/app/order/stat")
    fun getAllOrders () : Observable<Any>
}