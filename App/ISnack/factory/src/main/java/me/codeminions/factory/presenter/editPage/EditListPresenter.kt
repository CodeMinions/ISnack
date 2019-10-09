package me.codeminions.factory.presenter.editPage

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.snackModel.SnackModel
import java.util.ArrayList

class EditListPresenter(val view: EditListConstract.EditListView) : EditListConstract.EditListPresenter {

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

    override fun start() {

    }

    override fun destroy() {

    }

}