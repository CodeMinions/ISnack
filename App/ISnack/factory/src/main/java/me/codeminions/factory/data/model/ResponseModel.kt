package me.codeminions.factory.data.model

import java.io.Serializable
import java.util.*

data class ResponseModel<M>(val code: Int,
                            val message: String,
                            val time: Date = Date(),
                            val result: M?) : Serializable {

}

// 成功
val SUCCEED = 1
// 未知错误
val ERROR_UNKNOWN = 0

// 没有找到用户信息
val ERROR_NOT_FOUND_USER = 4041

// 创建用户失败
val ERROR_CREATE_USER = 3001

// 请求参数错误
val ERROR_PARAMETERS = 4001

// 服务器错误
val ERROR_SERVICE = 5001

// 账户Token错误，需要重新登录
val ERROR_ACCOUNT_TOKEN = 2001
// 账户登录失败
val ERROR_ACCOUNT_LOGIN = 2002
// 账户注册失败
val ERROR_ACCOUNT_REGISTER = 2003
// 没有权限操作
val ERROR_ACCOUNT_NO_PERMISSION = 2010
