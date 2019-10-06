package me.codeminions.factory.presenter.account

import android.graphics.Bitmap
import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.RegisterModel

interface AccountRegisterContract {
    interface AccountRegisterView: BaseContract.BaseView<AccountRegisterPresenter> {

        fun getUserName(): String

        fun getUserPwd1(): String
        fun getUserPwd2(): String

        fun getUserBirthY(): String
        fun getUserBirthM(): String

        fun getSex(): String

        fun cleanAllInfo()

        /**
         * 进度条操作
         */
        fun showRegistering()
        fun hideRegistering()

        /**
         * 回调操作
         */
        fun onRegisterSuccess(user: User)
        fun onRegisterFail(info: String)

        /**
         * 头像
         */
        fun chooseGet()     // 选择获取头像的方式：拍照/图库

        fun startFile()
        fun startPhoto()

        fun setPortrait(pic: Bitmap)

        fun sendPortrait(pic: Bitmap)
    }

    interface AccountRegisterPresenter: BaseContract.BasePresenter {
        fun register(name: String,
                     pwd1: String, pwd2: String,
                     birthY: String, birthM: String,
                     sex: String,
                     port: Bitmap)

        fun postPic(userId: String, pic: Bitmap)

    }
}