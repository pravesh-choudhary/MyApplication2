package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.BannerAdapter
import com.example.myapplication.adapter.onItemClickListener
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.repository.BannerRepository
import com.example.myapplication.viewModelFactory.BannerFactory
import com.example.myapplication.viewmodel.BannerViewModel


private lateinit var binding:ActivityHomeBinding
var data=ArrayList<String>()
lateinit var viewModel:BannerViewModel
val adapter=BannerAdapter()

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel=ViewModelProvider(this,BannerFactory(BannerRepository())).get(BannerViewModel::class.java)
        binding.lstBanner.adapter= adapter
        viewModel.getBanner().value?.let { adapter.setBannerList(it) }
        viewModel.bnr.observe(this, Observer {
            data.addAll(it)
            adapter.setBannerList(data)
        })

        binding.lstBanner.layoutManager=LinearLayoutManager(this)

        adapter.setOnItemClickListener(object : onItemClickListener {
            override fun onItemClick(str: String) {
               val i= Intent(this@HomeActivity,BannerDetailsActivity::class.java)
                val bundle=Bundle()
                bundle.putString("name",str)
                i.putExtras(bundle)
                startActivity(i)
            }
        })


    }
}