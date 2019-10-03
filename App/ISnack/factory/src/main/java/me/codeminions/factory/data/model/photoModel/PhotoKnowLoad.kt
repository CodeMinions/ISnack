package me.codeminions.factory.data.model.photoModel

import android.util.Log
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.ApiService
import me.codeminions.factory.net.RequestResult
import me.codeminions.factory.net.RetrofitService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PhotoKnowLoad {
    private var apiService: ApiService = RetrofitService.getApiService()

    fun getPhotoResult(part: MultipartBody.Part, requestResult: RequestResult<Snack>) {
        apiService.getPicKnowRes(part).enqueue(object: Callback<ResponseModel<Snack>> {
            override fun onResponse(call: Call<ResponseModel<Snack>>, response: Response<ResponseModel<Snack>>) {
                if (response.isSuccessful){
                    val responseModel = response.body() as ResponseModel<Snack>
                    requestResult.onSuccess(responseModel)
                }else {
                    requestResult.onError(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<Snack>>, t: Throwable) {
                requestResult.onError("ServerError : ${t.message}")
                Log.i("ffresult", t.message)
            }
        })
    }
}
