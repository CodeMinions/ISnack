package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.baseModel.*
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SnackModel : ISnackModel {

    override fun loadAll(callBack: ResponseCallBack<List<Snack>>) {

        RetrofitService.getApiService().getAllSnack().enqueue(object : Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>,
                                    response: Response<ResponseModel<List<Snack>>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<List<Snack>>

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

    override fun loadRecommend(callback: ResponseCallBack<List<Snack>>) {

        RetrofitService.getApiService().getRecommend().enqueue(object : Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>,
                                    response: Response<ResponseModel<List<Snack>>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<List<Snack>>

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

    override fun getSnackInfoById(id: Int, callback: ResponseCallBack<List<SnackInfoModel>>) {
        RetrofitService.getApiService().getSnackInfoById(id).enqueue(object : Callback<ResponseModel<SnackInfo>> {
            override fun onResponse(call: Call<ResponseModel<SnackInfo>>,
                                    response: Response<ResponseModel<SnackInfo>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<SnackInfo>
                    if (responseModel.code != 1)
                        callback.onFail(responseModel.message)
                    else
                        callback.onSuccess("Get All Data", response = snackInfoUnpack(responseModel.result!!))
                } else {
                    callback.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<SnackInfo>>, t: Throwable) {
                callback.onFail("Server Error ${t.message}")
            }
        })
    }

    override fun getMarkInfo(snackId: Int, callback: ResponseCallBack<IntArray>) {
        RetrofitService.getApiService().getMarkInfo(snackId).enqueue(object : Callback<ResponseModel<IntArray>> {
            override fun onResponse(call: Call<ResponseModel<IntArray>>,
                                    response: Response<ResponseModel<IntArray>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<IntArray>
                    if (responseModel.code != 1)
                        callback.onFail(responseModel.message)
                    else
                        callback.onSuccess("GET info", response = responseModel.result!!)
                } else {
                    callback.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<IntArray>>, t: Throwable) {
                callback.onFail("Server Error : ${t.message}")
            }
        })
    }

    override fun sendSnackList(model: SnackListModel, callback: ResponseCallBack<String>) {
        RetrofitService.getApiService().sendSnackList(model).enqueue(object : Callback<ResponseModel<String>> {
            override fun onResponse(call: Call<ResponseModel<String>>,
                                    response: Response<ResponseModel<String>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<String>
                    if (responseModel.code != 1)
                        callback.onFail(responseModel.message)
                    else
                        callback.onSuccess("ok", responseModel.message)
                } else {
                    callback.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                callback.onFail("Server Error : ${t.message}")
            }
        })
    }


    override fun loadByTag(tag: String, listener: ResponseCallBack<Snack>) {

    }

    override fun cancel(id: String) {
    }

}