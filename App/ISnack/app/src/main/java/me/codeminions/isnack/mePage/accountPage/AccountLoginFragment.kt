package me.codeminions.isnack.mePage.accountPage

import android.content.Context
import android.view.View
import android.widget.Toast
import me.codeminions.factory.PresenterFragment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.presenter.account.AccountLoginContract
import me.codeminions.factory.presenter.account.AccountLoginPresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentLoginBinding

class AccountLoginFragment : PresenterFragment<FragmentLoginBinding>(),
        AccountLoginContract.AccountLoginView {

    private lateinit var presenter: AccountLoginPresenter

    private lateinit var trigger: AccountTrigger

    override fun initPresenter(): AccountLoginContract.AccountLoginPresenter {
        return AccountLoginPresenter(this)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initWidget(root: View) {
        super.initWidget(root)
        dataBinding.handler = this
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        trigger = context as AccountTrigger
    }

    public fun onClickLogin(v: View) {
//        presenter.login()
        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show()
        onLoginSuccess(User())
    }

    override fun onLoginSuccess(user: User) {

        trigger.onTrigger()
    }

    override fun onLoginFail(info: String) {

    }

    override fun getUserName(): String {
        return ""
    }

    override fun getUserPwd(): String {
        return ""
    }

    override fun cleanUserName() {

    }

    override fun cleanUserPwd() {

    }

    override fun showLogining() {

    }

    override fun hideLogining() {

    }

    override fun setPresenter(presenter: AccountLoginContract.AccountLoginPresenter?) {

    }
}