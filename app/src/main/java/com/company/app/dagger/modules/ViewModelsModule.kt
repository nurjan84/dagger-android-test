package com.company.app.dagger.modules

import androidx.lifecycle.ViewModel
import com.company.app.dagger.scopes.ViewModelKey
import com.company.app.mvvm.viewmodels.CheckSMSViewModel
import com.company.app.mvvm.viewmodels.LoginViewModel
import com.company.app.mvvm.viewmodels.MainFragmentViewModel
import com.company.app.mvvm.viewmodels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CheckSMSViewModel::class)
    abstract fun bindCheckSMSViewModel(viewModel: CheckSMSViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel):ViewModel
}