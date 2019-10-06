package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.data.netData.Constant
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SnackModel : ISnackModel {

    override fun loadAll(callBack: ResponseCallBack<List<Snack>>) {

        RetrofitService.getApiService().getAllSnack().enqueue(object: Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>, response: Response<ResponseModel<List<Snack>>>) {
                if(response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<List<Snack>>
                    responseModel.result

                    callBack.onSuccess("Get All Data", response = responseModel.result!!)
                } else {
                    callBack.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<Snack>>>, t: Throwable) {
                callBack.onFail("Server Error ${t.message}")
            }
        })
    }

    override fun getSnackInfoById(id: Int, callback: ResponseCallBack<SnackInfo>) {
        RetrofitService.getApiService().getAllSnack().enqueue(object: Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>, response: Response<ResponseModel<List<Snack>>>) {
                if(response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<List<Snack>>
                    responseModel.result

                    callback.onSuccess("Get All Data", response = responseModel.result!!)
                } else {
                    callback.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<Snack>>>, t: Throwable) {
                callback.onFail("Server Error ${t.message}")
            }
        })
    }

    override fun loadByTag(tag: String, listener: ResponseCallBack<Snack>) {

    }

    override fun cancel(id: String) {
    }

}