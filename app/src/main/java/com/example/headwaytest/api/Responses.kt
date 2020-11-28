package com.example.headwaytest.api

import com.google.gson.annotations.SerializedName

data class Response(@SerializedName("total_count") val totalCount: Int,
                    @SerializedName("items") val items: List<RepoItem>)

data class RepoItem(@SerializedName("id") val id: Int,
                    @SerializedName("name") val name: String,
                    @SerializedName("owner") val owner: Owner,
                    @SerializedName("html_url") val htmlUrl: String,
                    var isViewed: Boolean)

data class Owner(@SerializedName("id") val id: Int,
                 @SerializedName("login") val login: String)