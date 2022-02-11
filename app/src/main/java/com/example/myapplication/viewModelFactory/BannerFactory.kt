package com.example.myapplication.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.BannerRepository
import com.example.myapplication.viewmodel.BannerViewModel

class BannerFactory(private val bnrRepository: BannerRepository) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(BannerViewModel::class.java)) {
             BannerViewModel(this.bnrRepository) as T
        }else
            throw IllegalArgumentException("UnknownViewModel")
    }
}