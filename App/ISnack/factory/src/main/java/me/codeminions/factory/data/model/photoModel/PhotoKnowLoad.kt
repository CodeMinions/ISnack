package me.codeminions.isnack.net

import android.graphics.Bitmap
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.ApiService
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoKnowLoad {
    private var apiService: ApiService = RetrofitService.getApiService()

    fun getPhotoResult(pic: Bitmap): Snack {
        apiService.getPicKnowRes().enqueue()
    }
}
