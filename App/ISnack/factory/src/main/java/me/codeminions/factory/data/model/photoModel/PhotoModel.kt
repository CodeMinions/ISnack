package me.codeminions.factory.data.model.photoModel

import android.util.Log
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseCallBack
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.RequestResult
import me.codeminions.factory.presenter.snackPhoto.SnackPhotoContract
import okhttp3.MultipartBody

class PhotoModel(val presenter: SnackPhotoContract.SnackPhotoPresenter) : IPhotoModel {

    private lateinit var callBack: ResponseCallBack<Snack>
    private var isCancel: Boolean = false

    // 向后端发送请求，识别图片
    override fun requestResult(photo: MultipartBody.Part, callBack: ResponseCallBack<Snack>) {
        this.callBack = callBack
        isCancel = false

        PhotoKnowLoad().getPhotoResult(photo, object : RequestResult<Snack> {
            override fun onSuccess(responseModel: ResponseModel<Snack>) {
                if (!isCancel) {
                    callBack.onSuccess("Result have got", responseModel.result!!)
                    Log.i("result", responseModel.result.toString())
                }else {
                    callBack.onFail("You cancel the request")
                }
            }

            override fun onError(error: String) {
                callBack.onFail(error)
            }
        })
    }

    override fun cancelRequest() {
        isCancel = true
    }
}
