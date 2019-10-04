package me.codeminions.factory.net

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Multipart
    @POST(value = "know/pic")
    fun getPicKnowRes(@Part file: MultipartBody.Part): Call<ResponseModel<Snack>>


}
