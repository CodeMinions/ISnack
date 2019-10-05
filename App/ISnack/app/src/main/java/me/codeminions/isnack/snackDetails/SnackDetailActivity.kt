package me.codeminions.isnack.snackDetails

import android.content.Context
import android.content.Intent
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.ActivitySnackDetailsBinding

class SnackDetailActivity : DataBindingActivity<ActivitySnackDetailsBinding>() {

    companion object {
        fun startAction(context: Context, snackId: Int) {
            val intent = Intent(context, SnackDetailActivity::class.java)
            intent.putExtra("SnackId", snackId)
            context.startActivity(intent)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_snack_details
    }

    override fun initWidget() {
        super.initWidget()
    }

}