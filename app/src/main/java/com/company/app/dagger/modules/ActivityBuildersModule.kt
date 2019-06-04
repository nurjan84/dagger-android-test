package com.company.app.dagger.modules

import com.company.app.dagger.scopes.ActivityScope
import com.company.app.mvvm.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentsBuildersModule::class, ViewModelsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}