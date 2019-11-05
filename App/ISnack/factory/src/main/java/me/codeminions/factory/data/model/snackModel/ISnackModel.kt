package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.SnackInfoModel
import me.codeminions.factory.data.model.baseModel.SnackListModel

interface ISnackModel {
    fun loadAll(callBack: ResponseCallBack<List<Snack>>)

    fun loadRecommend(callback: ResponseCallBack<List<Snack>>)

    fun loadByTag(tag: String, listener: ResponseCallBack<Snack>)

    fun getSnackInfoById(id: Int, callback: ResponseCallBack<List<SnackInfoModel>>)

    fun getMarkInfo(snackId: Int, callback: ResponseCallBack<IntArray>)

    fun sendSnackList(model: SnackListModel, callback: ResponseCallBack<String>)

    fun cancel(id : String)
}