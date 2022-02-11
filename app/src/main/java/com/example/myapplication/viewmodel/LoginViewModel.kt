package com.example.myapplication.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Login

class LoginViewModel:ViewModel() {
    var email=MutableLiveData<String>()
    var pass=MutableLiveData<String>()
    private var login=MutableLiveData<Login>()

    fun getLogin():MutableLiveData<Login>{
        if(login==null){
            login= MutableLiveData<Login>()
        }
        return login
    }

    fun onClick(view: View){
        var newLogin=Login(email.value.toString(),pass.value.toString())
        login.value=newLogin
    }
}