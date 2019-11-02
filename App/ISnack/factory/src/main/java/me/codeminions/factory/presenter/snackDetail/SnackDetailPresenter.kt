package me.codeminions.factory.presenter.snackDetail

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.CommentModel
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.SnackInfoModel
import me.codeminions.factory.data.model.snackModel.SnackModel

class SnackDetailPresenter(val view: SnackDetailContract.SnackDetailView) :
        SnackDetailContract.SnackDetailPresenter {
    private val snackModel = SnackModel()

    private val commentModel = me.codeminions.factory.data.model.commentModel.CommentModel()

    init {
        view.setPresenter(this)
    }

    override fun getCommentUser(userId: Int, callBack: ResponseCallBack<User>) {
        commentModel.getUserById(userId, callBack)
    }

    override fun getSnackMore(snackID: Int) {
        snackModel.getSnackInfoById(snackID, object: ResponseCallBack<List<SnackInfoModel>> {
            override fun onSuccess(info: String, response: List<SnackInfoModel>) {
                view.loadMoreSuccess(response)
            }

            override fun onFail(info: String) {

            }
        })
    }

    override fun getComment(snackId: Int) {
        commentModel.getCommentBySnack(snackId, object: ResponseCallBack<List<CommentModel>> {
            override fun onSuccess(info: String, response: List<CommentModel>) {
                view.loadCommentSuccess(response)
            }
            override fun onFail(info: String) {

            }
        })
    }

    override fun getMark(snackId: Int) {
        snackModel.getMarkInfo(snackId, object: ResponseCallBack<IntArray> {
            override fun onSuccess(info: String, response: IntArray) {
                view.showMarkInfo(response)
            }

            override fun onFail(info: String) {
                view.showTip(info)
            }
        })
    }

    override fun start() {

    }

    override fun destroy() {

    }

}