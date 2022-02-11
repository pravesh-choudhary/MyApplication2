package com.example.myapplication.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.CartItem
import com.example.myapplication.repository.CartRepository

class CartViewModel(private var app: Application): ViewModel() {
    var cartItems=MutableLiveData<List<CartItem>>()
    var cartRepository=CartRepository(app)

    fun getCartItemsAll():MutableLiveData<List<CartItem>>{
        if(cartItems==null){
            cartItems=MutableLiveData<List<CartItem>>()
        }
        cartItems.postValue(cartRepository.getAllCartItems().value)
        return cartItems
    }

    fun setCartItems(cartitems:List<CartItem>){
        cartItems.value=cartitems
    }
}