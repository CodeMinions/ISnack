package me.codeminions.factory.presenter.snackMain

import android.content.Context
import androidx.core.content.FileProvider
import java.io.File

class SnackMainPresenter(var view: SnackMainContract.SnackMainView<SnackMainContract.SnackMainPresenter>) :
        SnackMainContract.SnackMainPresenter {

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



    override fun jumpToMe(context: Context) {
        view.jumpToMe(context)
    }


    override fun start() {

    }

    override fun destroy() {

    }
}
