package com.nytarticles.service

import com.nytarticles.model.response.ResArticles
import retrofit2.Call
import retrofit2.http.*

interface APIClient {

    @GET("mostviewed/all-sections/{period}.json")
    fun getArticles(@Path("period") periodInDays: Int, @QueryMap map: Map<String, String>): Call<ResArticles>

}