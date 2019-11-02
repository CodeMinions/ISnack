package me.codeminions.factory.data.model.commentModel

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.CommentModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack

interface ICommentModel {

    fun getCommentBySnack(snackId: Int, callBack: ResponseCallBack<List<CommentModel>>)

    fun sendComment(model: CommentModel, callBack: ResponseCallBack<Any>)

    fun getUserById(userId: Int, callBack: ResponseCallBack<User>)
}