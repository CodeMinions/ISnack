package me.codeminions.factory.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import me.codeminions.factory.data.bean.User

private const val FILE_NAME = "ISNACK_ACCOUNT_DATA"
private const val KEY_ID = "ACCOUNT_ID"
private const val KEY_NAME = "ACCOUNT_NAME"
private const val KEY_PWD = "ACCOUNT_PWD"
private const val KEY_TAG = "ACCOUNT_TAG"

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
 * 用户退出登录
 */
fun setLoginOut(context: Context) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    editor.putBoolean(KEY_TAG, false)
    editor.apply()
}
