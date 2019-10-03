package me.codeminions.isnack.photoResult

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import me.codeminions.factory.data.bean.Snack
import me.codeminions.factory.presenter.snackPhoto.SnackPhotoContract
import me.codeminions.factory.presenter.snackPhoto.SnackPhotoPresenter
import me.codeminions.isnack.R
import me.codeminions.isnack.databinding.FragmentPhotoResultBinding

class PhotoResultFragment : DialogFragment(), SnackPhotoContract.SnackPhotoView<SnackPhotoPresenter> {

    lateinit var binding : FragmentPhotoResultBinding
    private lateinit var presenter: SnackPhotoContract.SnackPhotoPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photo_result, container, false)
        binding.isShowProgress = true

        initPresenter()
        showBitMap()
        return binding.root
    }

    override fun showBitMap() {
        val bitmap = arguments?.getParcelable("pic") as Bitmap
        binding.bitmap = bitmap

        sendPicInfo(bitmap)
    }

    override fun sendPicInfo(pic: Bitmap) {
        presenter.getResult(pic)
    }

    override fun getPicResult(snack: Snack) {
        binding.snackName = snack.name
    }

    override fun onClickDetails() {

    }

    override fun showProgress(isShow: Boolean) {
        binding.isShowProgress = isShow
    }

    override fun setPresenter(presenter: SnackPhotoPresenter?) {
        this.presenter = presenter!!
    }

    private fun initPresenter() : SnackPhotoContract.SnackPhotoPresenter {
        return SnackPhotoPresenter(this)
    }

    override fun showTips(info: String) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.cancel()
    }
}
