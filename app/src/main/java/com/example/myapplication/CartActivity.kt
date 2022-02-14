package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.CartAdapter
import com.example.myapplication.adapter.onDeleteItemClickListener
import com.example.myapplication.databinding.ActivityCartBinding
import com.example.myapplication.viewModelFactory.CartFactory
import com.example.myapplication.viewmodel.CartViewModel
import com.example.myapplication.model.CartItem

private lateinit var binding: ActivityCartBinding

lateinit var cartViewModel: CartViewModel
public val cartAdapter= CartAdapter()
class CartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        var cartDataList=ArrayList<CartItem>()
        val name=intent.extras?.getString("name")
        val price=intent.extras?.getInt("price")

        val newcartItem= price?.let { CartItem(it,name) }

        binding=DataBindingUtil.setContentView(this,R.layout.activity_cart)
        cartViewModel=ViewModelProvider(this,CartFactory(application)).get(CartViewModel::class.java)

        binding.recycleCart.adapter= cartAdapter
        if (newcartItem != null) {
            cartDataList.add(newcartItem)
            cartAdapter.setCartItems(cartDataList)
        }


        cartViewModel.cartItems.observe(this) {
            cartDataList= it as ArrayList<CartItem>
            cartAdapter.setCartItems(it)
        }


        binding.recycleCart.layoutManager=LinearLayoutManager(this)
        if (newcartItem != null) {
            cartViewModel.cartRepository.insert(newcartItem)
        }

        cartAdapter.setOnDeleteItemClickListener(object :onDeleteItemClickListener{
            override fun onItemClick(cartItem: CartItem) {
                cartViewModel.cartRepository.delete(cartItem)
            }
        })

        binding.btnCheckout.setOnClickListener{
            cartViewModel.cartRepository.deleteAll()
            cartAdapter.cartitems.clear()
            startActivity(Intent(this,SuccessActivity::class.java))
        }

    }
}