package me.codeminions.factory.net

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.LoginModel
import me.codeminions.factory.data.model.RegisterModel
import me.codeminions.factory.data.model.ResponseModel
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /**
     * 图片识别
     */
    @Multipart
    @POST(value = "know/pic")
    fun getPicKnowRes(@Part file: MultipartBody.Part): Call<ResponseModel<Snack>>

    /**
     * 登录注册
     */
    @POST(value = "account/login")
    fun loginAccount(@Body model: LoginModel): Call<ResponseModel<User>>

    @POST(value = "account/register")
    fun registerModel(@Body model: RegisterModel): Call<ResponseModel<User>>

    // 头像上传
    @Multipart
    @POST(value = "account/port")
    fun postPortrait(@Query("id") userId: String, @Part file: MultipartBody.Part): Call<ResponseModel<String>>

}
