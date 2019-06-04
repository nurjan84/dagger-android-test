package com.company.app.dagger.modules

import android.app.Application
import android.content.Context
import com.company.app.BuildConfig
import com.company.app.api.ServiceAPI
import com.company.app.dagger.scopes.AppScope
import com.company.app.utils.Utils
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @AppScope
    @Provides
    fun provideContext(app:Application):Context{
        return app
    }

    @AppScope
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, provideRxAdapter: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(provideRxAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @AppScope
    @Provides
    fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @AppScope
    @Provides
    fun  getApiService(retrofit: Retrofit): ServiceAPI {
        return retrofit.create(ServiceAPI::class.java)
    }

    @AppScope
    @Provides
    fun  getUtils(context: Context): Utils {
        return Utils(context)
    }

}