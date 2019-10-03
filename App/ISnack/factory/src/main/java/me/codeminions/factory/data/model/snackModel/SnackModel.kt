package me.codeminions.factory.data.model.snackModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.netData.Constant

class SnackModel : ISnackModel {

    lateinit var listener: ResponseCallBack<Snack>

    private val list = ArrayList<Snack>()

    override fun loadAll(): ArrayList<Snack> {

//        listener.onSuccess("获取本地信息")
        list.addAll(Constant.list)

        return list
    }

    override fun loadByTag(tag: String, listener: ResponseCallBack<Snack>): List<Snack> {
        return ArrayList()
    }

    override fun cancel(id: String) {
    }

}