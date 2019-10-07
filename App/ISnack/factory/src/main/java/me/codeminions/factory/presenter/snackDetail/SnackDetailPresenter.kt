package me.codeminions.factory.presenter.snackDetail

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.data.model.SnackInfoModel
import me.codeminions.factory.data.model.snackModel.SnackModel

class SnackDetailPresenter(val view: SnackDetailContract.SnackDetailView) : SnackDetailContract.SnackDetailPresenter {

    private val model = SnackModel()

    init {
        view.setPresenter(this)
    }

    override fun getSnackMore(snackID: Int) {
        model.getSnackInfoById(snackID, object: ResponseCallBack<List<SnackInfoModel>>{
            override fun onSuccess(info: String, response: List<SnackInfoModel>) {
                view.loadMoreSuccess(response)
            }

            override fun onFail(info: String) {

            }
        })
    }

    override fun getComment(snackId: Int) {
        model.getCommentById(snackId, object: ResponseCallBack<List<Comment>> {
            override fun onSuccess(info: String, response: List<Comment>) {
                view.loadCommentSuccess(response)
            }
            override fun onFail(info: String) {

            }
        })
    }

    override fun start() {

    }

    override fun destroy() {

    }

}