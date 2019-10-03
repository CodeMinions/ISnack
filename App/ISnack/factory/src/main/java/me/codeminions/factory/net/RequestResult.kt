package me.codeminions.factory.net

import me.codeminions.factory.data.model.ResponseModel

interface RequestResult<T> {

    fun onSuccess(responseModel: ResponseModel<T>)

    fun onError(error: String)

}