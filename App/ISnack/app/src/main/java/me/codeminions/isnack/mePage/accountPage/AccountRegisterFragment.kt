package me.codeminions.isnack.mePage.accountPage

import me.codeminions.factory.PresenterFragment
import me.codeminions.factory.presenter.account.AccountRegisterContract
import me.codeminions.factory.presenter.account.AccountRegisterPresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentRegisterBinding

class AccountRegisterFragment : PresenterFragment<FragmentRegisterBinding>(),
        AccountRegisterContract.AccountRegisterView {
    override fun setPresenter(presenter: AccountRegisterContract.AccountRegisterPresenter?) {

    }

    override fun initPresenter(): AccountRegisterContract.AccountRegisterPresenter {
        return AccountRegisterPresenter(this)
    }

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_register
    }

}