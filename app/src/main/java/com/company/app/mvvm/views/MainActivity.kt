package com.company.app.mvvm.views

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.company.app.R
import com.company.app.mvvm.viewmodels.MainViewModel
import com.company.app.mvvm.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private val mainViewModel: MainViewModel by lazy {  ViewModelProviders.of(this, providerFactory).get(MainViewModel::class.java) }
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, _, _ -> hideKeyboard() }

        val appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.loginFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        tokenErrorListener()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()


    private fun tokenErrorListener(){
        mainViewModel.tokenError().observe(this, Observer{ error ->
            if(error == true){
                navController.navigate(R.id.goto_login)
            }
            mainViewModel.setTokenError(null)
        })
    }
}
