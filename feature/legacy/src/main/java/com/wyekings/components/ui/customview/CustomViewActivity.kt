package com.wyekings.components.ui.customview

import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.wyekings.base.BaseActivity
import com.wyekings.components.R
import com.wyekings.components.databinding.ActivityCustomViewBinding
import com.wyekings.uikit.insets.insetter.applyStatusBarInsets
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomViewActivity : BaseActivity(R.layout.activity_custom_view) {

    private val viewBinding by viewBinding(ActivityCustomViewBinding::bind)
    private val viewModel by viewModels<CustomViewViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyWindowInsets()
        bindUi()
    }

    private fun applyWindowInsets() {
        viewBinding.toolbar.applyStatusBarInsets()
    }

    private fun bindUi() {
        with(viewBinding) {
            val tabModels = viewModel.tabModels
            viewPager.offscreenPageLimit = 1
            viewPager.adapter =
                CustomViewFragmentStateAdapter(this@CustomViewActivity, tabModels)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabModels[position].title
            }.attach()
        }
    }
}