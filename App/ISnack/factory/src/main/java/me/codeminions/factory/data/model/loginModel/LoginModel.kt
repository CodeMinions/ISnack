package me.codeminions.factory.data.model.loginModel

import android.os.AsyncTask
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.data.model.ResponseCallBack
import java.lang.ref.WeakReference

class LoginModel : ILoginModel {

    val handler = Handler()

    private lateinit var callback: ResponseCallBack<User>

    override fun login(model: me.codeminions.factory.data.model.LoginModel, callback: ResponseCallBack<User>) {
        this.callback = callback
        val task = MyTask(this)
        task.execute()
    }

    class MyTask(model: LoginModel): AsyncTask<User, Int, String>(){
        var reference: WeakReference<LoginModel> = WeakReference(model)

        override fun doInBackground(vararg params: User?): String {

            SystemClock.sleep(3000)

            val user = User("123", "半截拖鞋")

            val model = reference.get()

            model?.handler?.post{
                model.callback.onSuccess("ok", user)
                Log.i("tips","start callback")
            }

            return "操作结束"
        }
    }
}