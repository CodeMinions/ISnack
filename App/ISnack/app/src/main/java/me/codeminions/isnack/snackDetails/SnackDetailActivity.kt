package me.codeminions.isnack.snackDetails

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.common.mvp.BaseContract
import me.codeminions.factory.PresenterActivity
import me.codeminions.factory.PresenterFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.presenter.snackDetail.SnackDetailContract
import me.codeminions.factory.presenter.snackDetail.SnackDetailPresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivitySnackDetailsBinding

class SnackDetailActivity : PresenterActivity<ActivitySnackDetailsBinding>(),
        SnackDetailContract.SnackDetailView {
    override fun initPresenter(): SnackDetailContract.SnackDetailPresenter {
        return SnackDetailPresenter(this)
    }

    private lateinit var presenter: SnackDetailContract.SnackDetailPresenter

    companion object {
        fun startAction(context: Context, snack: Snack) {
            val intent = Intent(context, SnackDetailActivity::class.java)
            intent.putExtra("SnackId", snack)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_snack_details
    }

    override fun initWidget() {
        super.initWidget()
    }

    override fun initData() {

        binding.handler = this
        val snack = intent.extras?.getSerializable("SnackId") as Snack
        Log.i("snackinfo", snack.toString())

        binding.snack = snack
        binding.imgUrl = URL_PIC + snack.img

        presenter.getSnackMore(snackID = snack.snackID!!)
    }

    override fun lookComment() {

    }

    override fun loadInfoSuccess() {

    }

    override fun loadMoreSuccess() {

    }

    override fun setPresenter(presenter: SnackDetailContract.SnackDetailPresenter?) {
        this.presenter = presenter!!
    }

    override fun showTip(info: String?) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }
}