package me.codeminions.factory.data.model.loginModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.LoginModel
import me.codeminions.factory.data.model.ResponseCallBack

interface ILoginModel {

    fun login(model : LoginModel, callback: ResponseCallBack<User>)
}