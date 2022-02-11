package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "price") val price:Int,
    @ColumnInfo(name="name") val name:String?
) {


}