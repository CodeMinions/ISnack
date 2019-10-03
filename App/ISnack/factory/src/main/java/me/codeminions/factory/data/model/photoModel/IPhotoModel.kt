package me.codeminions.factory.data.model.photoModel

import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.data.model.ResponseCallBack
import okhttp3.MultipartBody

interface IPhotoModel {

    fun requestResult(photo: MultipartBody.Part, callBack: ResponseCallBack<Snack>)

    fun cancelRequest()
}
