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

        list.forEachIndexed { index, snack ->
            run {
                snack.tag = index
                snack.infos["热量"] = "500kg"
                snack.infos["蛋白质"] = "5.80kg"
                snack.infos["脂肪"] = "31.0kg"
                snack.infos["碳水化合物"] = "56kg"
                snack.infos["膳食纤维"] = "5kg"
                snack.infos["钠"] = "2kg"
            }
        }
        return list
    }

    override fun loadByTag(tag: String, listener: ResponseCallBack<Snack>): List<Snack> {
        return ArrayList()
    }

    override fun cancel(id: String) {
    }

}