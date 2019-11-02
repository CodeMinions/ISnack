package me.codeminions.factory.presenter.mePage

import me.codeminions.factory.data.bean.Comment
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.SnackListModel
import me.codeminions.factory.data.model.userModel.UserModel

class MePagePresenter(val view: MePageContract.MePageView) : MePageContract.MePagePresenter {

    private val userModel = UserModel()

    init {
        view.setPresenter(this)
    }

    override fun getCommentByUser(userId: Int) {
        userModel.getCommentByUser(userId, object: ResponseCallBack<List<Comment>> {
            override fun onSuccess(info: String, response: List<Comment>) {
                view.refreshList(response)
            }

            override fun onFail(info: String) {
                view.showTip("Error : $info")
            }
        })
    }

    override fun getSnackListByUser(userId: Int) {
        userModel.getSnackListByUser(userId, object: ResponseCallBack<List<SnackListModel>> {
            override fun onSuccess(info: String, response: List<SnackListModel>) {
                view.refreshOtherList(response)
            }

            override fun onFail(info: String) {
                view.showTip("Error : $info")
            }

        })
    }

    override fun start() {

    }

    override fun destroy() {

    }

}