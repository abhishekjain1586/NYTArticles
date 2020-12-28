package com.nytarticles.service

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceHelper {

    val TAG = ServiceHelper::class.java.simpleName

    var logging = HttpLoggingInterceptor()
    private const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/";

    init {
        logging.level = HttpLoggingInterceptor.Level.BODY
    }

    fun getClient(): APIClient {
        val okHttpClient = OkHttpClient.Builder().let {
            it.connectTimeout(1, TimeUnit.MINUTES)
            it.readTimeout(30, TimeUnit.SECONDS)
            it.writeTimeout(1, TimeUnit.MINUTES)
            it.addInterceptor(logging)
            it.build()
        }

        val retrofit = Retrofit.Builder().let {
            it.baseUrl(BASE_URL)
            it.addConverterFactory(GsonConverterFactory.create())
            it.client(okHttpClient)
            it.build()
        }

        return retrofit.create(APIClient::class.java)
    }
}