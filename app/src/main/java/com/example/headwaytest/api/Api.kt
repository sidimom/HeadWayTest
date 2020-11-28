package com.example.headwaytest.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/search/repositories?sort=stars")
    fun getRepositories(@Query("q") query: String,
                        @Query("page") page: Int,
                        @Query("per_page") perPage: Int): Single<Response>
}