package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBannerDetailsBinding

private lateinit var binding:ActivityBannerDetailsBinding

class BannerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_details)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_banner_details)
        var name=intent.extras?.get("name").toString()
        binding.txtName.text= name
        binding.btnBuy.setOnClickListener{
            var b=Bundle()
            b.putInt("price",90)
            b.putString("name",name)
            var i= Intent(this,CartActivity::class.java)
            i.putExtras(b)
            startActivity(i)
        }
    }
}