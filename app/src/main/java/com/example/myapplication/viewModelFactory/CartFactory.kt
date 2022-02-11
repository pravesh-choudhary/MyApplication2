package com.example.myapplication.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.CartRepository
import com.example.myapplication.viewmodel.BannerViewModel
import com.example.myapplication.viewmodel.CartViewModel

class CartFactory(public var cartRepository: CartRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CartViewModel::class.java)){
            return CartViewModel(cartRepository) as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }
}