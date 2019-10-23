package me.codeminions.factory.net

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.*
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
    fun postPortrait(@Part("id") id: Int,
                     @Part file: MultipartBody.Part): Call<ResponseModel<String>>

    /**
     * 零食模块
     */
    @GET(value = "snack/all")
    fun getAllSnack() :Call<ResponseModel<List<Snack>>>

    @GET(value = "snack/recommend")
    fun getRecommend(): Call<ResponseModel<List<Snack>>>

    @GET(value = "snack/getSnackInfo")
    fun getSnackInfoById(@Query("id")id: Int) : Call<ResponseModel<SnackInfo>>

    @GET(value = "snack/getCommentBySnack")
    fun getCommentBySnack(@Query("id")id: Int) : Call<ResponseModel<List<Comment>>>

    @GET(value = "snack/search")
    fun searchSnack(@Query("name")name: String) : Call<ResponseModel<List<Snack>>>

    /**
     * 用户模块
     */
    @GET(value = "user/get")
    fun getUserById(@Query("id")id: Int): Call<ResponseModel<User>>

    @GET(value = "snack/getCommentByUser")
    fun getCommentByUser(@Query("id")id: Int) :Call<ResponseModel<List<Comment>>>

    // 获取所有人的评价
    @GET(value = "snack/allRecommend")
    fun getAllComment(): Call<ResponseModel<List<Comment>>>

    // 发送评价
    @POST(value = "snack/setComment")
    fun sendComment(@Body model: CommentModel): Call<ResponseModel<String>>

    @GET(value = "snack/getMark")
    fun getMarkInfo(@Query("id")id: Int): Call<ResponseModel<IntArray>>

    // 根据用户获取用户清单
    @GET(value = "snack/getSnackList")
    fun getSnackListByUser(@Query("id")userId: Int): Call<ResponseModel<List<SnackListModel>>>

    // 展示所有用户清单
    @GET(value = "snack/getAllSnackList")
    fun getAllSnackList(): Call<ResponseModel<List<SnackListModel>>>

    // 用户发送清单
    @POST(value = "snack/setSnackList")
    fun sendSnackList(@Body model: SnackListModel): Call<ResponseModel<String>>
}
