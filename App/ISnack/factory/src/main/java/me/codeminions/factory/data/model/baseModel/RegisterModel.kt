package me.codeminions.factory.data.model.baseModel

import java.io.Serializable

data class RegisterModel(var name: String? = null,
                         var pwd: String? = null,
                         var sex: String? = null,
                         var birth: String? = null):Serializable {

}