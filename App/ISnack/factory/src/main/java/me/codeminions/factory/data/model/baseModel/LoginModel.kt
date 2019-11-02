package me.codeminions.factory.data.model.baseModel

import java.io.Serializable

data class LoginModel(var userName: String? = null,
                      var pwd: String? = null): Serializable {

}