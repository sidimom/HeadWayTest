package com.example.headwaytest.api

import com.example.headwaytest.utils.Const
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NetworkService private constructor() {
    var api: Api

    companion object{
        private val instance = NetworkService()
        fun getInstance(): NetworkService = instance
    }

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        api = retrofit.create(Api::class.java)
    }
}