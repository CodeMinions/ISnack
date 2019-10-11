package me.codeminions.factory.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import me.codeminions.factory.data.bean.User
import android.util.Base64
import android.util.Log
import com.google.gson.Gson
import java.io.*


private const val FILE_NAME = "ISNACK_ACCOUNT_DATA"
private const val KEY_ID = "ACCOUNT_ID"
private const val KEY_NAME = "ACCOUNT_NAME"
private const val KEY_PWD = "ACCOUNT_PWD"
private const val KEY_TAG = "ACCOUNT_TAG"

private const val KEY_ALL = "ACCOUNT_ALL"

/**
 * 保存用户的登录信息
 */
fun saveAccountData(context: Context, vararg args: String) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    editor.putString(KEY_ID, args[0])
    editor.putString(KEY_NAME, args[1])
    editor.putString(KEY_PWD, args[2])

    editor.putBoolean(KEY_TAG, true)

    editor.apply()
}

/**
 * 获取本地的用户登录状态
 */
fun getLoginStatus(context: Context): Boolean {
    val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    return pref.getBoolean(KEY_TAG, false)
}

/**
 * 获取本地的用户登录信息
 */
fun getLoginInfo(context: Context): User? {
    val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    if (!getLoginStatus(context))
        return null

    return User(pref.getString(KEY_NAME, "")!!,
            pref.getString(KEY_ID, "")!!,
            pref.getString(KEY_PWD, "")!!)
}

/**
 * 获取本地的用户ID
 */
fun getUserId(context: Context): String {
    val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    if (!getLoginStatus(context))
        return ""

    return pref.getString(KEY_ID, "")!!
}

/**
 * 用户退出登录
 */
fun setLoginOut(context: Context) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    editor.putBoolean(KEY_TAG, false)
    editor.apply()
}

/**
 * 使用json保存本地信息
 */
fun saveLocalJson(context: Context, user: User) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    val data = Gson().toJson(user)
    editor.putString(KEY_ALL, data)
    editor.apply()
}

fun getLocalJson(context: Context): User? {
    val sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    val data = sharedPreferences.getString(KEY_ALL, "")

    return if (data.isNotEmpty()) {
        Gson().fromJson<User>(data, User::class.java)
    }else{
        null
    }
}


/**
 * 保存对象信息流
 */
fun saveLocalStream(context: Context, user: User, key: String) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    val baos = ByteArrayOutputStream()
    editor.putString(KEY_ALL, user.name)
    try {
        val outputStream = ObjectOutputStream(baos)
        outputStream.writeObject(user)
        val temp = String(Base64.encode(baos.toByteArray(), Base64.DEFAULT))
        editor.putString(key, temp)

    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        editor.apply()
    }
}

fun getLocalStream(context: Context, key: String): User? {
    val sharedPreferences = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    val temp = sharedPreferences.getString(KEY_ALL, "")
    if (temp != null && temp.isNotEmpty()) {
        val bais = ByteArrayInputStream(Base64.decode(temp.toByteArray(), Base64.DEFAULT))
        var user: User? = null
        try {
            val ois = ObjectInputStream(bais)
            user = ois.readObject() as User
        } catch (e: Exception) {
            Log.e("ShareError", e.localizedMessage)
        }
        return user!!
    }
    return null
}

