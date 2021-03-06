package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CartItemLayoutBinding
import com.example.myapplication.model.CartItem
import com.example.myapplication.room.CartDatabase

class CartAdapter :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){
   val cartitems=ArrayList<CartItem>()
    lateinit var listener:onDeleteItemClickListener

    public fun setOnDeleteItemClickListener(listener: onDeleteItemClickListener){
        this.listener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        var layout= LayoutInflater.from(parent.context)
        var binding=CartItemLayoutBinding.inflate(layout,parent,false)
        return CartViewHolder(binding)
    }

    public fun setCartItems(lst:List<CartItem>){
        cartitems.addAll(lst)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        var cart=cartitems.get(position)
        holder.binding.txtNameItem.text=cart.name
        holder.binding.txtPriceItem.text=cart.price.toString()
        holder.binding.btnDeleteItem.setOnClickListener{
            listener.onItemClick(cart)
            cartitems.remove(cart)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return cartitems.size
    }


    inner class CartViewHolder(var binding:CartItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }
}

public interface onDeleteItemClickListener{
    fun onItemClick(cartItem: CartItem)
}

