package me.codeminions.isnack.mePage.accountPage

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.fragment_register.*
import me.codeminions.factory.PresenterFragment
import me.codeminions.factory.data.bean.User
import me.codeminions.factory.presenter.account.AccountRegisterContract
import me.codeminions.factory.presenter.account.AccountRegisterPresenter
import me.codeminions.factory.utils.saveAccountData
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentRegisterBinding
import java.io.File


class AccountRegisterFragment : PresenterFragment<FragmentRegisterBinding>(),
        AccountRegisterContract.AccountRegisterView {

    private lateinit var presenter: AccountRegisterContract.AccountRegisterPresenter

    private lateinit var trigger: AccountTrigger

    override fun getContentLayoutId(): Int {
        return R.layout.fragment_register
    }

    override fun initWidget(root: View) {
        super.initWidget(root)
        dataBinding.rootRegister.setBackgroundResource(R.drawable.v2_80b9172a683e901b7a04a77a0e5d7455_r)
        dataBinding.handler = this
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        trigger = context as AccountTrigger
    }

    override fun getUserName(): String {
        return edit_register_userName.text.toString()
    }

    override fun getUserPwd1(): String {
        return edit_register_pwd1.text.toString()
    }

    override fun getUserPwd2(): String {
        return edit_register_pwd2.text.toString()
    }

    override fun getUserBirthY(): String {
        return ed_res_year.text.toString()
    }

    override fun getUserBirthM(): String {
        return ed_res_mou.text.toString()
    }

    override fun getSex(): String {
        return if (btn_res_changeMale.isSelected) "male" else "famale"
    }

    fun onClickMale(v: View) {
        btn_res_changeMale.isSelected = true
        btn_res_changeFamale.isSelected = false
    }

    fun onClickFamale(v: View) {
        btn_res_changeMale.isSelected = false
        btn_res_changeFamale.isSelected = true
    }

    // 点击显示密码文本
    fun onClickPwd(v: View) {
        when (v.id) {
            R.id.btn_register_visible2 -> {
                if (v.isSelected) {
                    edit_register_pwd2.transformationMethod = PasswordTransformationMethod.getInstance();
                    v.isSelected = false
                } else {
                    edit_register_pwd2.transformationMethod = HideReturnsTransformationMethod.getInstance();
                    v.isSelected = true
                }
            }
            R.id.btn_register_visible1 -> {
                if (v.isSelected) {
                    edit_register_pwd2.transformationMethod = PasswordTransformationMethod.getInstance();
                    v.isSelected = false
                } else {
                    edit_register_pwd2.transformationMethod = HideReturnsTransformationMethod.getInstance();
                    v.isSelected = true
                }
            }
        }
    }

    fun onClickPortrait(v: View) {
        chooseGet()
    }

    fun onClickRegister(v: View) {
        Log.i("点击", "注册")
        presenter.register(getUserName(),
                getUserPwd1(), getUserPwd2(),
                getUserBirthY(), getUserBirthM(),
                getSex(),
                port)
    }

    override fun cleanAllInfo() {

    }

    override fun showRegistering() {
        load.visibility = View.VISIBLE
        btn_register_r.visibility = View.INVISIBLE
    }

    override fun hideRegistering() {
        load.visibility = View.INVISIBLE
        btn_register_r.visibility = View.VISIBLE
    }

    override fun onRegisterSuccess(user: User) {
        saveAccountData(context!!, user.userID.toString(), user.name!!, "")
        trigger.onTrigger()
    }

    override fun onRegisterFail(info: String) {

    }

    override fun showTip(info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    override fun chooseGet() {
        AlertDialog.Builder(context).setTitle("选择图片来源")
                .setItems(arrayOf("图库", "拍照")) { _, which ->
                    when (which) {
                        0 -> startFile()
                        1 -> startPhoto()
                    }
                }.show()
    }

    override fun startFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2)
    }

    override fun startPhoto() {
        val file = File(context?.externalCacheDir, "cache_image.jpg")
        if (file.exists())
            file.delete()

        file.createNewFile()
        val uri = FileProvider.getUriForFile(context!!, "me.codeminions.isnack.fileprovider", file)

        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)

        startActivityForResult(intent, 1)
    }

    fun photoZoom(uri: Uri) {
        //构建隐式Intent来启动裁剪程序
        val intent = Intent("com.android.camera.action.CROP")
        //设置数据uri和类型为图片类型
        intent.setDataAndType(uri, "image/*")
        //显示View为可裁剪的
        intent.putExtra("crop", true)
        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1)
        intent.putExtra("aspectY", 1)
        //输出图片的宽高均为150
        intent.putExtra("outputX", 150)
        intent.putExtra("outputY", 150)
        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", true)
        startActivityForResult(intent, 3)
    }

    private lateinit var port: Bitmap
    override fun setPortrait(pic: Bitmap) {
        port = pic
        img_portrait.setImageBitmap(pic)
    }

    override fun sendPortrait(pic: Bitmap) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null)
            return
        when (requestCode) {
            1 -> photoZoom(data.data!!)
//                val bitmap = BitmapFactory.decodeStream(context?.contentResolver?.openInputStream(data.data!!))
            2 -> photoZoom(data.data!!)
            3 -> {
                val bitmap = data.extras?.getParcelable<Bitmap>("data")
                setPortrait(bitmap!!)
            }
        }
    }

    override fun setPresenter(presenter: AccountRegisterContract.AccountRegisterPresenter?) {
        this.presenter = presenter!!
    }

    override fun initPresenter(): AccountRegisterContract.AccountRegisterPresenter {
        return AccountRegisterPresenter(this)
    }

}