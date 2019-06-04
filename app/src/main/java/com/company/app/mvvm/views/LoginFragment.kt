package com.company.app.mvvm.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.company.app.R
import com.company.app.databinding.FragmentLoginBinding
import com.company.app.mvvm.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : BaseFragment() {

    private val loginViewModel: LoginViewModel by lazy {  ViewModelProviders.of(this, providerFactory).get(LoginViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_login, container, false)
        binding.loginViewModel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loaderListener()
        getSMSSuccessListener()
        errorListener()
    }

    private fun loaderListener(){
        loginViewModel.loader().observe(viewLifecycleOwner, Observer{ show ->
            if(show == true){
                showLoader()
            }else{
                hideLoader()
            }
        })
    }

    private fun getSMSSuccessListener(){
        loginViewModel.getSMSSuccess().observe(viewLifecycleOwner, Observer{ success ->
            if(success!=null && success){
                loginViewModel.setSMSSuccess(null)
                Navigation.findNavController(getSMSCodeButton).navigate(LoginFragmentDirections.gotoCheckSMS(loginViewModel.phoneNumber.value.toString()))
            }
        })
    }

    private fun errorListener(){
        loginViewModel.error().observe(viewLifecycleOwner, Observer{ error ->
            if(error!=null){
                showErrorMessage(error.message)
                loginViewModel.error.postValue(null)
            }
        })
    }

}
