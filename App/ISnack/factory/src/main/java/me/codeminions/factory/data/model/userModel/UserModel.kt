package me.codeminions.factory.data.model.userModel

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.ResponseModel
import me.codeminions.factory.data.model.baseModel.SnackListModel
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserModel {

    fun getCommentByUser(userId: Int, callback: ResponseCallBack<List<Comment>>) {
        RetrofitService.getApiService().getCommentByUser(userId)
                .enqueue(object : Callback<ResponseModel<List<Comment>>> {
                    override fun onResponse(call: Call<ResponseModel<List<Comment>>>,
                                            response: Response<ResponseModel<List<Comment>>>) {
                        if (response.isSuccessful) {
                            val list = response.body()?.result as List<Comment>
                            callback.onSuccess("ok", list)
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel<List<Comment>>>, t: Throwable) {

                    }
                })
    }

    fun getSnackListByUser(userId: Int, callback: ResponseCallBack<List<SnackListModel>>) {
        RetrofitService.getApiService().getSnackListByUser(userId).enqueue(object : Callback<ResponseModel<List<SnackListModel>>> {
            override fun onResponse(call: Call<ResponseModel<List<SnackListModel>>>, response: Response<ResponseModel<List<SnackListModel>>>) {
                if (response.isSuccessful) {
                    if (response.body()?.code == 1) {
                        val list = response.body()?.result as List<SnackListModel>
                        callback.onSuccess("ok", list)
                    } else {
                        callback.onFail(response.body()?.message!!)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel<List<SnackListModel>>>, t: Throwable) {
                callback.onFail("ServerError : ${t.message}")
            }
        })
    }
}
