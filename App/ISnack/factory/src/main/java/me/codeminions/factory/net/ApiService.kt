package me.codeminions.factory.net

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @Headers({
        "User-Agent: android",
        "apiKey: 2828"
    })

    @Headers("User-Agent: android")


    @GET("know/pic")
    fun getPicKnowRes(): ResponseModel<Snack>
}
