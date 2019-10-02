package me.codeminions.isnack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import me.codeminions.common.widget.BaseViewPagerAdapter
import me.codeminions.factory.data.bean.Snack

class SnackDetailFragment : DialogFragment() {

    private lateinit var snack: Snack

    private lateinit var viewPager: ViewPager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_snack_details, container)

        snack = arguments?.get("data") as Snack

        viewPager = view.findViewById(R.id.vp_details)

        viewPager.adapter = object : BaseViewPagerAdapter(null, childFragmentManager, SnackDetailInfoFragment(), SnackDetailCommentFragment()) {
            override fun getBundle(position: Int): Bundle? {

                val bundle = Bundle()
                bundle.putSerializable("data", snack)
                return bundle
            }
        }
        return view
    }

}