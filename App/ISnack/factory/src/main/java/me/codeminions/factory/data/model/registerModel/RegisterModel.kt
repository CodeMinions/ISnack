package me.codeminions.factory.data.model.registerModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.RegisterModel
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.RetrofitService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterModel : IRegisterModel {

    private lateinit var callBack: ResponseCallBack<User>

    private lateinit var callBackPort: ResponseCallBack<String>

    override fun register(model: RegisterModel, callBack: ResponseCallBack<User>) {
        this.callBack = callBack

        RetrofitService.getApiService().registerModel(model).enqueue(object : Callback<ResponseModel<User>> {
            override fun onResponse(call: Call<ResponseModel<User>>, response: Response<ResponseModel<User>>) {
                if (response.isSuccessful){
                    val responseModel = response.body() as ResponseModel<User>
                    callBack.onSuccess("ok", response = responseModel.result!!)
                }else {
                    callBack.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<User>>, t: Throwable) {
                callBack.onFail("Server Error : ${t.message}")
            }
        })
    }

    override fun registerPort(id: String, pic: MultipartBody.Part, callBack: ResponseCallBack<String>) {

        RetrofitService.getApiService().postPortrait(id, pic).enqueue(object : Callback<ResponseModel<String>> {
            override fun onResponse(call: Call<ResponseModel<String>>, response: Response<ResponseModel<String>>) {
                if (response.isSuccessful) {
                    val responseModel = response.body() as ResponseModel<String>
                    callBack.onSuccess("ok", responseModel.result!!)
                }else{
                    callBack.onFail(response.message())
                }
            }

            override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {
                callBack.onFail("Server Error : ${t.message}")
            }
        })
    }
}