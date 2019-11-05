package me.codeminions.factory.presenter.snackMain

import android.content.Context
import androidx.core.content.FileProvider
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.mainPageModel.MainPageModel
import java.io.File

class SnackMainPresenter(var view: SnackMainContract.SnackMainView<SnackMainContract.SnackMainPresenter>) :
        SnackMainContract.SnackMainPresenter {

    val model = MainPageModel()
    init {
        view.setPresenter(this)
    }

    override fun startPhoto(context: Context) {
        val file = File(context.externalCacheDir, "cache_image.jpg")

        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()

        val uri = FileProvider.getUriForFile(context, "me.codeminions.isnack.fileprovider", file)

        view.startPhoto(uri)
    }

    override fun searchSnack(content: String) {
        model.searchSnack(content, object : ResponseCallBack<List<Snack>> {
            override fun onSuccess(info: String, response: List<Snack>) {
                view.initSearchList(response)
            }

            override fun onFail(info: String) {
                view.showTip("Error: $info")
                // 不是服务器错误
                if(!info.contentEquals("ServerError")) {
                    // 隐藏加载
                    view.hintProgress()
                }
            }
        })
    }


    override fun jumpToMe(context: Context) {
        view.jumpToMe(context)
    }


    override fun start() {

    }

    override fun destroy() {

    }
}
