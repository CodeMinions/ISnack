package me.codeminions.factory.presenter.account

import android.graphics.Bitmap
import androidx.fragment.app.Fragment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.RegisterModel
import me.codeminions.factory.data.model.ResponseCallBack
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class AccountRegisterPresenter(val view: AccountRegisterContract.AccountRegisterView) : AccountRegisterContract.AccountRegisterPresenter {
    private val model = me.codeminions.factory.data.model.registerModel.RegisterModel()

    init {
        view.setPresenter(this)
    }

    override fun register(name: String,
                          pwd1: String, pwd2: String,
                          birthY: String, birthM: String,
                          sex: String,
                          port: Bitmap) {
        if(pwd1 != pwd2
                || birthY.toInt() < 1900 || birthY.toInt() > 2019) {
            view.showTip("输入非法")
            return
        }
        view.showRegistering()

        val registerModel = RegisterModel(name, pwd1, sex, "$birthY-$birthM")
        model.register(registerModel, object : ResponseCallBack<User> {
            override fun onSuccess(info: String, response: User) {
                view.onRegisterSuccess(response)
                view.showTip("注册成功，已为您登陆")

                view.hideRegistering()

                postPic(response.userID!!, pic = port)
            }

            override fun onFail(info: String) {
                view.onRegisterFail("注册失败 ： $info")
                view.showTip("重新注册")

                view.hideRegistering()
            }
        })

    }

    override fun postPic(userId: String, pic: Bitmap) {
        model.registerPort(userId, getFilePart(pic), object: ResponseCallBack<String> {
            override fun onSuccess(info: String, response: String) {
                view.showTip("头像已上传")
            }
            override fun onFail(info: String) {
                view.showTip(info)
            }
        })
    }

    private fun getFilePart(pic: Bitmap): MultipartBody.Part {
        val file = File((view as Fragment).context!!.externalCacheDir, "image.jpg")
        try {
            if (file.exists()) {
                file.delete()
            }
            file.createNewFile()

            val bos = BufferedOutputStream(FileOutputStream(file))
            pic.compress(Bitmap.CompressFormat.JPEG, 100, bos)
            bos.flush()
            bos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val requestBody1 = RequestBody.create(MediaType.parse("image/png"), file)
        return MultipartBody.Part.createFormData("file", file.name, requestBody1)
    }

    override fun start() {

    }

    override fun destroy() {

    }

}