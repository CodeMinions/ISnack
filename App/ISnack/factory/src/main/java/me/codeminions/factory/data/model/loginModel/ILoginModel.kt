package me.codeminions.factory.data.model.loginModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.LoginModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack

interface ILoginModel {

    fun login(model : LoginModel, callback: ResponseCallBack<User>)
}