package me.codeminions.factory.presenter.account

import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.baseModel.ResponseCallBack
import me.codeminions.factory.data.model.loginModel.LoginModel

class AccountLoginPresenter(val view: AccountLoginContract.AccountLoginView) : AccountLoginContract.AccountLoginPresenter {

    val model = LoginModel()

    init {
        view.setPresenter(this)
    }

    override fun login(userName: String, userPwd: String) {
        view.showLogining()
        if(userName.isNotEmpty() && userPwd.isNotEmpty()) {
            val loginModel = me.codeminions.factory.data.model.baseModel.LoginModel(userName, userPwd)
            model.login(loginModel, object : ResponseCallBack<User> {
                override fun onSuccess(info: String, response: User) {
                    view.onLoginSuccess(response)
                    view.showTip("登陆成功")

                    view.hideLogining()
                }

                override fun onFail(info: String) {
                    view.onLoginFail("登录失败 ： $info")
                    view.showTip("请重新输入")

                    view.hideLogining()
                }
            })
        }else {
            view.showTip("请输入..")
            view.hideLogining()
        }

    }

    override fun clean() {

    }

    override fun start() {

    }

    override fun destroy() {

    }

}