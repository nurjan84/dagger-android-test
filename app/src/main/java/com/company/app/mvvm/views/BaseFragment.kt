package com.company.app.mvvm.views

import android.app.AlertDialog
import android.widget.Toast
import com.company.app.R
import com.company.app.dialogs.LoaderDialog
import com.company.app.mvvm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment(){

    @Inject lateinit var providerFactory: ViewModelProviderFactory

    fun showToast(toastMessage : String?){
        Toast.makeText(
            activity,
            toastMessage,
            Toast.LENGTH_LONG)
            .show()
    }

    private var loader : LoaderDialog? = null
    fun showLoader(){
        if(loader == null){
            loader = LoaderDialog()
            loader!!.show(activity!!.supportFragmentManager,"LoaderDialog")
        }
    }
    fun hideLoader(){
        if(loader != null){
            loader!!.dismiss()
            activity!!.supportFragmentManager.beginTransaction().remove(loader!!)
            loader = null
        }
    }

    fun showErrorMessage(msg:String?){
        if(msg!=null){
            val simpleAlert = AlertDialog.Builder(activity).create()
            simpleAlert.setTitle(getString(R.string.error))
            simpleAlert.setMessage(msg)
            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok)) { _, _ ->
                simpleAlert.dismiss()
            }
            simpleAlert.show()
        }
    }

}