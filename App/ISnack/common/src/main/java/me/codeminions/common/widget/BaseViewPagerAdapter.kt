package me.codeminions.common.widget

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

abstract class BaseViewPagerAdapter(private val titles: Array<String>?,
                                    fm: FragmentManager,
                                    private vararg val fragments: Fragment) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    open fun getBundle(position: Int): Bundle? {
        return null
    }

    override fun getItem(position: Int): Fragment {
        val fragment: Fragment = if (position > fragments.size - 1)
            fragments[fragments.size - 1]
        else
            fragments[position]

        val bundle = getBundle(position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles?.get(position)
    }

}