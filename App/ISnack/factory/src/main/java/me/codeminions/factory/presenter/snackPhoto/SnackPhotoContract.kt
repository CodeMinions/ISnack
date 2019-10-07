package me.codeminions.factory.presenter.snackPhoto

import android.graphics.Bitmap
import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.Snack

interface SnackPhotoContract {
    interface SnackPhotoView<presenter: BaseContract.BasePresenter>: BaseContract.BaseView<presenter> {
        fun showBitMap()

        fun sendPicInfo(pic: Bitmap)

        fun getPicResult(snack: Snack)

        fun onClickDetails()

        fun showProgress(isShow: Boolean)
    }

    interface SnackPhotoPresenter: BaseContract.BasePresenter {

        fun getResult(pic: Bitmap)

        fun cancel()
    }
}
