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
import com.company.app.databinding.FragmentCheckSmscodeBinding
import com.company.app.mvvm.viewmodels.CheckSMSViewModel
import kotlinx.android.synthetic.main.fragment_check_smscode.*

class CheckSMSCodeFragment : BaseFragment() {

    private val checkSMSViewModel: CheckSMSViewModel by lazy {  ViewModelProviders.of(this, providerFactory).get(CheckSMSViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentCheckSmscodeBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_check_smscode, container, false)
        binding.checkSMSViewModel = checkSMSViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            checkSMSViewModel.phoneNumber = CheckSMSCodeFragmentArgs.fromBundle(it).phoneNumber
        }

        loaderListener()
        errorListener()
        loginSuccessListener()
    }

    private fun loaderListener(){
        checkSMSViewModel.loader().observe(viewLifecycleOwner, Observer{ show ->
            if(show == true){
                showLoader()
            }else{
                hideLoader()
            }
        })
    }

    private fun errorListener(){
        checkSMSViewModel.error().observe(viewLifecycleOwner, Observer{ error ->
            if(error!=null){
                showErrorMessage(error.message)
            }
        })
    }

    private fun loginSuccessListener(){
        checkSMSViewModel.loginSuccess().observe(viewLifecycleOwner, Observer{ success ->
            if(success == true){
                Navigation.findNavController(verifySMSCodeButton).navigate(CheckSMSCodeFragmentDirections.gotoMainFragment())
            }
        })
    }
}
