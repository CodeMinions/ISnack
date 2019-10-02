package me.codeminions.factory.data.model.snack

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.OnDownLoadListener

interface ISnackModel {
    fun loadAll() :List<Snack>

    fun loadByTag(tag: String, listener: OnDownLoadListener) :List<Snack>

    fun cancel(id : String)
}