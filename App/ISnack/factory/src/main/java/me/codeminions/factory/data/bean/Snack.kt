package me.codeminions.factory.data.bean

import java.io.Serializable

data class Snack(var snackID: Int? = null,
                 var name: String? = null,
                 var origin: String? = null,
                 var shelf: String? = null,
                 var mark: Float = 0.toFloat(),
// 图片的URL
                 var img: String? = null) : Serializable {

    constructor(name: String, mark: Float) : this() {
        this.name = name
        this.mark = mark
    }

    constructor(id: Int, name: String, mark: Float) : this() {
        this.snackID = id
        this.name = name
        this.mark = mark
    }
}