package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.viewmodel.LoginViewModel
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel:LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginViewModel=ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.composeView.setContent {
            var mail = rememberSaveable { mutableStateOf("") }
            var pass= rememberSaveable{ mutableStateOf("") }
            var isError= rememberSaveable{
                mutableStateOf(false)
            }
            Column(
                modifier = Modifier.fillMaxSize()
                    ,verticalArrangement = Arrangement.Center
            ){
                OutlinedTextField(value = mail.value,
                    onValueChange = {
                    mail.value=it
                    isError.value=false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start=20.dp,end=20.dp,top=20.dp),
                    placeholder = {
                        Text("Enter Email")
                    },
                    isError = isError.value,

                    )
                if(isError.value){
                  Text(
                      text = "Not a valid email address",
                      color= Color.Red,
                      modifier = Modifier
                          .padding(start=20.dp,top=5.dp)

                  )
                }
                OutlinedTextField(value = pass.value, onValueChange = {pass.value=it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    placeholder = {
                        Text("Enter Password")
                    }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                    ,
                    onClick = {
                        if(checkEmail(mail.value)){
                            startHome()
                            loginViewModel.email.value=mail.value
                            loginViewModel.pass.value=pass.value
                        }else{
                            isError.value=true
                        }
                     },
                ){
                    Text(
                        text = "Submit"
                    )
                }
            }
        }

    }

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    private fun checkEmail(email: String): Boolean {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
    }
    private fun startHome(){
        startActivity(Intent(applicationContext,HomeActivity::class.java))
    }
}

