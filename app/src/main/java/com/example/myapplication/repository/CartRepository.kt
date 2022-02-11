package com.example.myapplication.repository

import com.example.myapplication.model.CartItem
class CartRepository {
    var cart=ArrayList<CartItem>()

    fun getCartItems():ArrayList<CartItem>{
        val cartitem=CartItem("Apple",90)
        for(i in 1..5){
            cart.add(cartitem)
        }
        return cart
    }
}