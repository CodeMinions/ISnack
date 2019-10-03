package me.codeminions.factory.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService private constructor() {

    private lateinit var retrofit: Retrofit
    private lateinit var httpLoggingInterceptor: HttpLoggingInterceptor

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var apiService: ApiService

    companion object {
        private val instance = SingleHolder.holder
        fun getApiService(): ApiService {
            return instance.apiService
        }
    }

    private object SingleHolder {
        val holder = RetrofitService()
    }

    init {
        initLoggingInterceptor()
        initOkHttp()
        initRetrofit()
    }


    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    private fun initOkHttp() {
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .build()
    }

    private fun initLoggingInterceptor() {
        httpLoggingInterceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { Log.i("RetrofitLog", "retrofitBack = $it") })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

}

private const val URL_BASE = "http://192.168.115.46:8080/isnack/api/"

private const val DEFAULT_TIME_OUT = 30000L
private const val DEFAULT_READ_TIME_OUT = 30000L
