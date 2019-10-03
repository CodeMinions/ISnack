package me.codeminions.factory.presenter.account

import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.data.bean.User

interface AccountLoginContract {

    interface AccountLoginView: BaseContract.BaseView<AccountLoginPresenter> {
        fun onLoginSuccess(user: User)

        fun onLoginFail(info: String)

        fun getUserName(): String
        fun getUserPwd(): String

        fun cleanUserName()
        fun cleanUserPwd()

        /**
         * 进度条操作
         */
        fun showLogining()
        fun hideLogining()
    }

    interface AccountLoginPresenter: BaseContract.BasePresenter {
        /**
         * 触发一次登录
         */
        fun login(userName: String, userPwd: String)

        fun clean()
    }
}