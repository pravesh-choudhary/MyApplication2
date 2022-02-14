package com.example.myapplication.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.myapplication.model.CartItem
import com.example.myapplication.room.CartDao
import com.example.myapplication.room.CartDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartRepository(app: Application) {
    private val db=CartDatabase.getInstance(app)
    private var cartDao:CartDao = db.roomDao()
    private var cartItems:LiveData<List<CartItem>> = cartDao.getAllCartItems()

    fun insert(cartItem: CartItem){
        cartDao.insert(cartItem)
    }
    fun delete(cartItem: CartItem){cartDao.delete(cartItem)}

    fun getAllCartItems():LiveData<List<CartItem>>{
        return cartItems
    }
    fun deleteAll(){
        cartDao.deleteAll()
    }


}