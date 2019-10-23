package me.codeminions.factory.data.bean

import java.io.Serializable

data class User(var userID: Int? = 0,
                var name: String? = null,
                var passward: String? = null,
                var sex: String? = null,
                var birth: String? = null,
                var portrait: String? = null): Serializable {

    constructor(name: String, portrait: String, passward: String) : this() {
        this.name = name
        this.portrait = portrait
        this.passward = passward
    }

    constructor(userID: Int): this() {
        this.userID = userID
    }

}
