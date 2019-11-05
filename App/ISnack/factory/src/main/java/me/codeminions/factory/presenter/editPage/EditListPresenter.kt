package me.codeminions.factory.presenter.editPage

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.baseModel.SnackListModel
import me.codeminions.factory.data.model.snackModel.SnackModel
import java.text.SimpleDateFormat
import java.util.*

class EditListPresenter(val view: EditListConstract.EditListView) :
        EditListConstract.EditListPresenter {

    private val model = SnackModel()

    init {
        view.setPresenter(this)
    }

    override fun getSnackList() {
        model.loadAll(object : ResponseCallBack<List<Snack>> {
            override fun onSuccess(info: String, response: List<Snack>) {
                view.refreshSnackList(ArrayList(response))
            }

            override fun onFail(info: String) {
                view.showTip(info)
            }
        })
    }

    override fun sendSnackList(userId: Int, title: String,
                               snackList: List<Snack>, content: String) {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date())
        val snackListModel = SnackListModel(User(userId), title, snackList, content, date)
        model.sendSnackList(snackListModel, object : ResponseCallBack<String> {
            override fun onSuccess(info: String, response: String) {
                view.sendSuccess()
            }

            override fun onFail(info: String) {
                view.sendFail(info)
            }
        })
    }

    override fun start() {

    }

    override fun destroy() {

    }

}