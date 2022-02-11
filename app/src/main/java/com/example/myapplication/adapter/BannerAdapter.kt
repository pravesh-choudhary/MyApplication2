package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.BannerDetailsActivity
import com.example.myapplication.R
import com.example.myapplication.adapter
import com.example.myapplication.databinding.BannerLayoutBinding
import com.example.myapplication.model.CartItem

public class BannerAdapter: RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    val banners= ArrayList<String>()
    public lateinit var listener:onItemClickListener

    public fun setOnItemClickListener(listener: onItemClickListener){
        this.listener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=BannerLayoutBinding.inflate(inflater,parent,false)
        return BannerViewHolder(binding)
    }

    fun setBannerList(lst: ArrayList<String>){
        banners.addAll(lst)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val urlImg =banners[position]
        Glide.with(holder.itemView.context).load(R.drawable.dummy).into(holder.binding.imgBanner)

    }

    override fun getItemCount(): Int {
        return banners.size
    }


    inner class BannerViewHolder(val binding:BannerLayoutBinding):RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                listener.onItemClick(banners[adapterPosition])
            }
        }
    }

}
public interface onItemClickListener{
    fun onItemClick(str:String)
}