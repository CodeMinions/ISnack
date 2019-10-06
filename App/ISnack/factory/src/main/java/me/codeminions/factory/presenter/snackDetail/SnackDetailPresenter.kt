package me.codeminions.factory.presenter.snackDetail

import me.codeminions.factory.data.bean.SnackInfo
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.snackModel.SnackModel

class SnackDetailPresenter(val view: SnackDetailContract.SnackDetailView) : SnackDetailContract.SnackDetailPresenter {

    val model = SnackModel()

    override fun getSnackMore(snackID: Int) {
        model.
    }

    override fun start() {

    }

    override fun destroy() {

    }

}