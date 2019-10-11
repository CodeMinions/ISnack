package me.codeminions.isnack.starPage

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewDebug
import android.widget.EditText
import android.widget.Toast
import me.codeminions.common.app.DataBindingActivity
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.CommentModel
import me.codeminions.factory.data.model.ResponseModel
import me.codeminions.factory.net.RetrofitService
import me.codeminions.factory.net.URL_PIC
import me.codeminions.factory.utils.getUserId
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentStarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarActivity : DataBindingActivity<FragmentStarBinding>() {

    companion object {
        fun startAction(context: Context, snack: Snack) {
            val intent = Intent(context, StarActivity::class.java)
            intent.putExtra("data", snack)
            context.startActivity(intent)
        }
    }
    private lateinit var snack: Snack

    override fun getLayoutResId(): Int = R.layout.fragment_star

    override fun initWidget() {
        val snack = intent?.extras?.get("data") as Snack
        binding.snack = snack
        this.snack = snack
        binding.imgUrl = URL_PIC + snack.img
        binding.handle = this
    }

    override fun initData() {

    }

    fun getStar() : Float = binding.starRating.rating

    fun getComment(): String = binding.starEditor.text.toString()

    fun onClickSend(v: View) {
        // 发送评价

        val userId = getUserId(this)
        if (userId.isEmpty()) {
            AlertDialog.Builder(this).setTitle("是不是忘了登录哟").show()
        } else {
            val model = CommentModel(userId.toInt(), snack.snackID!!, getComment(), getStar())
            RetrofitService.getApiService().sendComment(model).enqueue(object: Callback<ResponseModel<String>> {
                override fun onResponse(call: Call<ResponseModel<String>>, response: Response<ResponseModel<String>>) {
                    if(response.isSuccessful) {
                        val responseModel = response.body() as ResponseModel
                        if(responseModel.code == 1){
                            showTips(responseModel.message)
                            finish()
                        } else {
                            showTips(responseModel.message)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseModel<String>>, t: Throwable) {

                }
            })
        }

        // 关闭页面
    }

    fun onClickBack(v: View) {
        finish()
    }

    fun showTips(info :String) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show()
    }

}