package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.CartItem
import com.example.myapplication.repository.CartRepository

class CartViewModel(private var cartRepository: CartRepository): ViewModel() {
    var cartItems=MutableLiveData<List<CartItem>>()


    fun getCartItemsAll():MutableLiveData<List<CartItem>>{
        if(cartItems==null){
            cartItems=MutableLiveData<List<CartItem>>()
        }
        cartItems.postValue(cartRepository.getCartItems())
        return cartItems
    }

    fun setCartItems(cartitems:List<CartItem>){
        cartItems.value=cartitems
    }
}