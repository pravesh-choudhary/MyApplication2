package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.BannerAdapter
import com.example.myapplication.adapter.CartAdapter
import com.example.myapplication.databinding.ActivityCartBinding
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.repository.CartRepository
import com.example.myapplication.viewModelFactory.CartFactory
import com.example.myapplication.viewmodel.BannerViewModel
import com.example.myapplication.viewmodel.CartViewModel
import com.example.myapplication.model.CartItem

private lateinit var binding: ActivityCartBinding
var cartData=ArrayList<CartItem>()
lateinit var cartViewModel: CartViewModel
public val cartAdapter= CartAdapter()
class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_cart)
        cartViewModel=ViewModelProvider(this,CartFactory(CartRepository())).get(CartViewModel::class.java)
        binding.recycleCart.adapter= cartAdapter

        cartViewModel.getCartItemsAll().value?.let { cartData.addAll(it) }
        cartAdapter.setCartItems(cartData)

        cartViewModel.cartItems.observe(this) {
            cartData.addAll(it)
            cartAdapter.setCartItems(it as ArrayList<CartItem>)
        }
        binding.recycleCart.layoutManager=LinearLayoutManager(this)
        binding.btnCheckout.setOnClickListener{
            startActivity(Intent(this,SuccessActivity::class.java))
        }

    }
}