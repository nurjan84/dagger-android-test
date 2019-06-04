package com.company.app.mvvm.views

import android.app.AlertDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.company.app.R
import com.company.app.dialogs.LoaderDialog
import dagger.android.support.DaggerAppCompatActivity

open class BaseActivity : DaggerAppCompatActivity(){
    fun showToast(toastMessage : String?){
        Toast.makeText(
            this,
            toastMessage,
            Toast.LENGTH_LONG)
            .show()
    }
    fun showKeyboard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun hideKeyboard(){
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private var loader : LoaderDialog? = null
    fun showLoader(){
        if(loader == null){
            loader = LoaderDialog()
            loader!!.show(supportFragmentManager,"LoaderDialog")
        }
    }
    fun hideLoader(){
        if(loader != null){
            loader!!.dismiss()
            supportFragmentManager.beginTransaction().remove(loader!!)
            loader = null
        }
    }

    fun showErrorMessage(msg:String?){
        if(msg!=null){
            val simpleAlert = AlertDialog.Builder(this).create()
            simpleAlert.setTitle(getString(R.string.error))
            simpleAlert.setMessage(msg)
            simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.ok)) { _, _ ->
                simpleAlert.dismiss()
            }
            simpleAlert.show()
        }
    }
}