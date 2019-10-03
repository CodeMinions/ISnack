package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseCallBack

interface ISnackModel {
    fun loadAll() :List<Snack>

    fun loadByTag(tag: String, listener: ResponseCallBack<Snack>) :List<Snack>

    fun cancel(id : String)
}