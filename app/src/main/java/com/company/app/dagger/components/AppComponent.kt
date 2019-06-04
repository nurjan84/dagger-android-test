package com.company.app.dagger.components

import android.app.Application
import com.company.app.App
import com.company.app.dagger.modules.*
import com.company.app.dagger.scopes.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@AppScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    ViewModelFactoryModule::class,
    AppModule::class,
    NetworkModule::class,
    RoomModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}