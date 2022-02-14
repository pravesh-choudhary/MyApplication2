package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityBannerDetailsBinding

private lateinit var binding:ActivityBannerDetailsBinding

class BannerDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_banner_details)
        var name=intent.extras?.get("name").toString()

        binding.composeViewBannerDetails.setContent {
            Scaffold() {
                BannerDetails(name,this)
            }
        }
    }
}
@Composable
private fun BannerDetails(name:String,context: Context){

    Column(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.dummy), contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
            ,
            alignment = Alignment.TopCenter
        )
        Text(color= Color.DarkGray,
            text=name,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
        )
        Text(
            text = stringResource(id = R.string.content),
            Modifier.padding(start=20.dp,end = 20.dp,top=9.dp),
            fontSize = 15.sp,
            textAlign = TextAlign.Justify
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text="Rs. "+"90",
                fontSize = 20.sp,

            )
            Button(
                onClick = { buttonClick(name,context)},
            ){
                Text(text="BUY NOW")
            }
        }
    }

}

private fun buttonClick(name:String,context: Context){
    var b=Bundle()
    b.putInt("price",90)
    b.putString("name",name)
    var i= Intent(context,CartActivity::class.java)
    i.putExtras(b)
    context.startActivity(i)
}