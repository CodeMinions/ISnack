package me.codeminions.factory.data.model

interface CallBackListener {
    fun onSuccess(info :String)

    fun onFail(info: String)
}