package me.codeminions.factory.data.model.mainPageModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.ResponseModel
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPageModel {

    fun searchSnack(content: String, callBack: ResponseCallBack<List<Snack>>) {
        RetrofitService.getApiService().searchSnack(content)
                .enqueue(object : Callback<ResponseModel<List<Snack>>> {
                    override fun onResponse(call: Call<ResponseModel<List<Snack>>>,
                                            response: Response<ResponseModel<List<Snack>>>) {
                        if (response.isSuccessful) {
                            val model = response.body()
                            if (model!!.code == 1) {
                                callBack.onSuccess("ok", response = model.result!!)
                            } else {
                                callBack.onFail(model.message)
                            }
                        } else {
                            callBack.onFail(response.message())
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel<List<Snack>>>, t: Throwable) {
                        callBack.onFail("ServerError: ${t.message}")
                    }
                })
    }
}