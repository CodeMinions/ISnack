package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.SnackInfoModel

interface ISnackModel {
    fun loadAll(callBack: ResponseCallBack<List<Snack>>)

    fun loadRecommend(callback: ResponseCallBack<List<Snack>>)

    fun loadByTag(tag: String, listener: ResponseCallBack<Snack>)

    fun getSnackInfoById(id: Int, callback: ResponseCallBack<List<SnackInfoModel>>)

    fun getCommentById(id: Int, callback: ResponseCallBack<List<Comment>>)

    fun cancel(id : String)
}