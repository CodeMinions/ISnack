package me.codeminions.factory.data.model

interface ResponseCallBack<T> {
    fun onSuccess(info :String, response: T)

    fun onFail(info: String)
}