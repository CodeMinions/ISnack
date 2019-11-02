package me.codeminions.factory.data.model.commentModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.CommentModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.ResponseModel
import me.codeminions.factory.net.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentModel : ICommentModel {
    override fun getUserById(userId: Int, callBack: ResponseCallBack<User>) {
        RetrofitService.getApiService().getUserById(userId).enqueue(object : Callback<ResponseModel<User>> {
            override fun onResponse(call: Call<ResponseModel<User>>, response: Response<ResponseModel<User>>) {
                if (response.isSuccessful) {
                    val user = response.body()?.result as User
                    callBack.onSuccess(response.message(), user)
                }
            }

            override fun onFailure(call: Call<ResponseModel<User>>, t: Throwable) {

            }
        })
    }

    override fun getCommentBySnack(snackId: Int, callBack: ResponseCallBack<List<CommentModel>>) {
        RetrofitService.getApiService().getCommentBySnack(snackId)
                .enqueue(object : Callback<ResponseModel<List<CommentModel>>> {
                    override fun onResponse(call: Call<ResponseModel<List<CommentModel>>>,
                                            response: Response<ResponseModel<List<CommentModel>>>) {
                        if (response.isSuccessful) {
                            val responseModel = response.body() as ResponseModel<List<CommentModel>>

                            callBack.onSuccess("Get All Data", response = responseModel.result!!)
                        } else {
                            callBack.onFail(response.message())
                        }
                    }

                    override fun onFailure(call: Call<ResponseModel<List<CommentModel>>>, t: Throwable) {
                        callBack.onFail("Server Error : ${t.message}")
                    }
                })
    }

    override fun sendComment(model: CommentModel, callBack: ResponseCallBack<Any>) {

    }


}