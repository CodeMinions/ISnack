package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack

interface ISnackModel {
    fun loadAll(callBack: ResponseCallBack<List<Snack>>)

    fun loadByTag(tag: String, listener: ResponseCallBack<Snack>)

    fun getSnackInfoById(id: Int, callback: ResponseCallBack<SnackInfo>)

    fun cancel(id : String)
}