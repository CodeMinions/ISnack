package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.data.model.SnackInfoModel
import me.codeminions.factory.data.model.snackInfoUnpack
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SnackModel : ISnackModel {

    override fun loadAll(callBack: ResponseCallBack<List<Snack>>) {

        RetrofitService.getApiService().getAllSnack().enqueue(object: Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>,
                                    response: Response<ResponseModel<List<Snack>>>) {
                if(response.isSuccessful) {
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

        RetrofitService.getApiService().getRecommend().enqueue(object: Callback<ResponseModel<List<Snack>>> {
            override fun onResponse(call: Call<ResponseModel<List<Snack>>>,
                                    response: Response<ResponseModel<List<Snack>>>) {
                if(response.isSuccessful) {
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
        RetrofitService.getApiService().getSnackInfoById(id).enqueue(object: Callback<ResponseModel<SnackInfo>> {
            override fun onResponse(call: Call<ResponseModel<SnackInfo>>,
                                    response: Response<ResponseModel<SnackInfo>>) {
                if(response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<SnackInfo>

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

    override fun getCommentById(id: Int, callback: ResponseCallBack<List<Comment>>) {
        RetrofitService.getApiService().getCommentBySnack(id).enqueue(object: Callback<ResponseModel<List<Comment>>> {
            override fun onResponse(call: Call<ResponseModel<List<Comment>>>,
                                    response: Response<ResponseModel<List<Comment>>>) {
                if(response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<List<Comment>>

                    callback.onSuccess("Get All Data", response = responseModel.result!!)
                } else {
                    callback.onFail(response.message())
                }
            }
            override fun onFailure(call: Call<ResponseModel<List<Comment>>>, t: Throwable) {
                callback.onFail("Server Error : ${t.message}")
            }
        })
    }

    override fun getMarkInfo(snackId: Int, callback: ResponseCallBack<IntArray>) {
        RetrofitService.getApiService().getMarkInfo(snackId).enqueue(object: Callback<ResponseModel<IntArray>> {
            override fun onResponse(call: Call<ResponseModel<IntArray>>,
                                    response: Response<ResponseModel<IntArray>>) {
                if(response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<IntArray>
                    if(responseModel.code != 1)
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

    override fun loadByTag(tag: String, listener: ResponseCallBack<Snack>) {

    }

    override fun cancel(id: String) {
    }

}