package me.codeminions.factory.data.bean

import java.io.Serializable

data class User(var id: Int = 0,
                var name: String? = null,
                var attent: Int = 0,
                var portrait: Int = 0): Serializable {

    constructor(name: String, portrait: Int, attent: Int) : this() {
        this.name = name
        this.portrait = portrait
        this.attent = attent
    }

}
