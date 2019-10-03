package me.codeminions.common.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

private const val FILE_NAME = "ISNACK_ACCOUNT_DATA"
private const val KEY_ID = "ACCOUNT_ID"
private const val KEY_PWD = "ACCOUNT_PWD"
private const val KEY_TAG = "ACCOUNT_TAG"

/**
 * 保存用户的登录信息
 */
fun saveAccountData(context: Context, vararg args:String) {
    val editor = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE).edit()

    editor.putString(KEY_ID, args[0])
    editor.putString(KEY_PWD, args[1])

    editor.putBoolean(KEY_TAG, true)

    editor.apply()
}

/**
 * 获取本地的用户登录信息
 */
fun getLoginStatus(context: Context): Boolean {
    val pref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    return pref.getBoolean(KEY_TAG, false)
}
