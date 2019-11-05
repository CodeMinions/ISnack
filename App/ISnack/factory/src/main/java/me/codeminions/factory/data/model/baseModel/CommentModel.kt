package me.codeminions.factory.data.model.baseModel

import me.codeminions.factory.data.bean.User
import java.io.Serializable

/**
 * 主要用于上传评论星级的model
 */
data class CommentModel(var send: User,
                        var snack_id: Int,
                        var content: String,
                        var star: Float) : Serializable {
}
