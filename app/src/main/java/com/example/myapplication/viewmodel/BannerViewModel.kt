package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.BannerRepository

class BannerViewModel(private val bnrRespository:BannerRepository): ViewModel() {
    var bnr= MutableLiveData<ArrayList<String>>()

    fun getBanner():MutableLiveData<ArrayList<String>>{
        if(bnr==null){
            bnr=MutableLiveData<ArrayList<String>>()
        }
        bnr.postValue(bnrRespository.getBanners())

        return bnr
    }



}