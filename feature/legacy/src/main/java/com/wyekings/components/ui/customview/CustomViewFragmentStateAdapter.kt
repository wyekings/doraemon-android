package com.wyekings.components.ui.customview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomViewFragmentStateAdapter(
    fragmentActivity: FragmentActivity, private val tabModels: List<TabModel>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = tabModels.size

    override fun createFragment(position: Int): Fragment = CustomViewFragment.newInstance(position)

}