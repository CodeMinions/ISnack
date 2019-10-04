package me.codeminions.factory.data.model

import java.io.Serializable

data class LoginModel(var userName: String? = null,
                      var pwd: String? = null): Serializable {

}