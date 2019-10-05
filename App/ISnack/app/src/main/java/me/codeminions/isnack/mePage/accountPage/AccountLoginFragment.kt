package me.codeminions.isnack.mePage.accountPage

import android.content.Context
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import me.codeminions.factory.utils.saveAccountData
import me.codeminions.factory.PresenterFragment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.presenter.account.AccountLoginContract
import me.codeminions.factory.presenter.account.AccountLoginPresenter
import me.codeminions.factory.utils.saveLocalJson
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentLoginBinding

class AccountLoginFragment : PresenterFragment<FragmentLoginBinding>(),
        AccountLoginContract.AccountLoginView {

    private lateinit var presenter: AccountLoginContract.AccountLoginPresenter

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
        presenter.login(getUserName(), getUserPwd())
//        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show()
//        onLoginSuccess(User())
    }

    override fun onLoginSuccess(user: User) {
        saveAccountData(context!!, user.userID!!, user.name!!, "")
        saveLocalJson(context!!, user)
        trigger.onTrigger()
    }

    override fun onLoginFail(info: String) {
        cleanUserPwd()
        showTip(info)
    }

    override fun getUserName(): String {
        return edit_login_userId.text.toString()
    }

    override fun getUserPwd(): String {
        return edit_login_pwd.text.toString()
    }

    override fun showTip(info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    override fun cleanUserName() {
        edit_login_userId.setText("")
    }

    override fun cleanUserPwd() {
        edit_login_pwd.setText("")
    }

    override fun showLogining() {
        btn_landr.visibility = View.INVISIBLE
        load.visibility = View.VISIBLE
    }

    override fun hideLogining() {
        btn_landr.visibility = View.VISIBLE
        load.visibility = View.INVISIBLE
    }

    override fun setPresenter(presenter: AccountLoginContract.AccountLoginPresenter?) {
        this.presenter = presenter!!
    }
}