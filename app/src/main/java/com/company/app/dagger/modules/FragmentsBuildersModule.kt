package com.company.app.dagger.modules

import com.company.app.mvvm.views.CheckSMSCodeFragment
import com.company.app.mvvm.views.LoginFragment
import com.company.app.mvvm.views.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeCheckSMSCodeFragment(): CheckSMSCodeFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment
}