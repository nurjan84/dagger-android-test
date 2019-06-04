package com.company.app.mvvm.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.company.app.R
import com.company.app.databinding.FragmentMainBinding
import com.company.app.mvvm.viewmodels.MainFragmentViewModel


class MainFragment : BaseFragment() {

    private val mainFragmentViewModel: MainFragmentViewModel by lazy { ViewModelProviders.of(this, providerFactory).get(MainFragmentViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate( inflater, R.layout.fragment_main, container, false)
        binding.mainFragmentViewModel = mainFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}
