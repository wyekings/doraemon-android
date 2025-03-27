package com.wyekings.components.ui.customview

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.base.BaseFragment
import com.wyekings.components.R
import com.wyekings.components.databinding.FragmentCustomViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomViewFragment @Inject constructor() : BaseFragment(R.layout.fragment_custom_view) {

    private val viewBinding by viewBinding(FragmentCustomViewBinding::bind)
    private val viewModel by activityViewModels<CustomViewViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
    }

    private fun bindUi() {
        val index = arguments?.getInt(BUNDLE_INDEX) ?: 0
        val view = viewModel.tabModels[index].viewClass.getDeclaredConstructor(Context::class.java)
            .newInstance(requireContext()) as View
        val lp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT
        )
        viewBinding.container.addView(view, lp)
    }

    companion object {

        private const val BUNDLE_INDEX = "bundle_index"
        fun newInstance(index: Int): Fragment {
            return CustomViewFragment().apply {
                arguments = bundleOf(BUNDLE_INDEX to index)
            }
        }
    }
}