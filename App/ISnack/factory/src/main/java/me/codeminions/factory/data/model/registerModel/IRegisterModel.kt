package me.codeminions.factory.data.model.registerModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.RegisterModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import okhttp3.MultipartBody

interface IRegisterModel {
    fun register(model: RegisterModel, callBack: ResponseCallBack<User>)

    fun registerPort(id: String, pic: MultipartBody.Part, callBack: ResponseCallBack<String>)
}