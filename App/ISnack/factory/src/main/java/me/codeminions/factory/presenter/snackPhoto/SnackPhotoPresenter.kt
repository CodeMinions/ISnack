package me.codeminions.factory.presenter.snackPhoto

import android.graphics.Bitmap
import android.os.Handler
import androidx.fragment.app.Fragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.photoModel.PhotoModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class SnackPhotoPresenter(val view: SnackPhotoContract.SnackPhotoView<SnackPhotoPresenter>)
    : SnackPhotoContract.SnackPhotoPresenter {

    private val photoModel: PhotoModel
    private val handler = Handler()

    init {
        view.setPresenter(this)
        photoModel = PhotoModel(this)
    }

    override fun getResult(pic: Bitmap) {

        photoModel.requestResult(getFilePart(pic),
                object : ResponseCallBack<Snack> {
                    override fun onSuccess(info: String, response: Snack) {
                        handler.post {
                            view.getPicResult(response)
                            view.showProgress(false)
                            view.showTip(info)
                        }
                    }

                    override fun onFail(info: String) {
                        handler.post {
                            view.showProgress(false)
                            view.showTip("怎么认不出来呢 ×_× :   $info")
                        }
                    }
                })
    }

    private fun getFilePart(pic: Bitmap): MultipartBody.Part {
        val file = File((view as Fragment).context!!.externalCacheDir, "image.jpg")
        try {
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()

            val bos = BufferedOutputStream(FileOutputStream(file))
            pic.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bos.flush()
            bos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val requestBody1 = RequestBody.create(MediaType.parse("image/png"), file)
        return MultipartBody.Part.createFormData("file", file.name, requestBody1)
    }

    override fun cancel() {
        photoModel.cancelRequest()
    }

    override fun start() {

    }

    override fun destroy() {

    }
}
