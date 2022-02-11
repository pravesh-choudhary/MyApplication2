package com.example.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.model.CartItem

@Database(entities = [CartItem::class], version = 1)
abstract class CartDatabase:RoomDatabase(){

    abstract fun roomDao(): CartDao

    companion object {
        private var instance: CartDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): CartDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, CartDatabase::class.java,
                    "cart_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }

}