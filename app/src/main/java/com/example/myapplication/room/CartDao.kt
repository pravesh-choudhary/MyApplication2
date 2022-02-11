package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.model.CartItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cartitem:CartItem)

    @Delete
    fun delete(cartitem: CartItem)

    @Query("SELECT * FROM cart")
    fun getAllCartItems():LiveData<List<CartItem>>

    @Query("DELETE FROM cart")
    fun deleteAll()

}