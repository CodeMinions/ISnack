package me.codeminions.factory.presenter.account

class AccountLoginPresenter(val view: AccountLoginContract.AccountLoginView) : AccountLoginContract.AccountLoginPresenter {

    init {
        view.setPresenter(this)
    }

    override fun login(userName: String, userPwd: String) {

    }

    override fun clean() {

    }

    override fun start() {

    }

    override fun destroy() {

    }

}