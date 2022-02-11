package com.example.myapplication.repository

class BannerRepository {

    var lst= listOf(
        "Apple",
        "Banana",
        "Cake",
        "Pencil",
        "Food",
        "Chips",
        "Roasted Beans",
        "Fruits",
        "Drink",
        "Coffee",
        "Tea"
    )

    fun getBanners():ArrayList<String>{
        val ls=ArrayList<String>()
        for(i in lst){
        ls.add(i)
        }
        return ls
    }

}