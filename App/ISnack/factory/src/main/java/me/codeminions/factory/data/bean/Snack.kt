package me.codeminions.factory.data.bean

import android.util.ArrayMap
import java.io.Serializable

data class Snack(var id: String? = null,
                 var name: String? = null,
                 var origin: String? = null,
                 var price: String? = null,
                 var mark: Float = 0.toFloat(),
                 var tag: Int = 0,
                 var heat: String? = null,
        // 图片的url
                 var img: String? = null,
                 var infos: ArrayMap<String, String> = ArrayMap()) : Serializable {

    constructor(name: String, mark: Float) : this() {
        this.name = name
        this.mark = mark
    }

}