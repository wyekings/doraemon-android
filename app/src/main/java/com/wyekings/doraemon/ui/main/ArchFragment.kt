package com.wyekings.doraemon.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.base.BaseFragment
import com.wyekings.base.ext.start
import com.wyekings.doraemon.R
import com.wyekings.doraemon.databinding.FragmentArchBinding
import com.wyekings.uikit.pager.Pager
import com.wyekings.uikit.recyclerview.GridDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *  @author wangpt on 9/5/23
 */
@AndroidEntryPoint
class ArchFragment @Inject constructor(): BaseFragment(R.layout.fragment_arch),Pager {

    @Inject lateinit var moduleAdapter:ModuleAdapter

    private val viewBinding by viewBinding(FragmentArchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
    }

    private fun bindUi() {
        with(viewBinding) {
            recyclerView.bind()
        }
    }
    private fun RecyclerView.bind() {
        adapter = moduleAdapter.setOnClickListener { start(it.clazz) }
        layoutManager = GridLayoutManager(requireContext(), SPAN_COUNT)
        addItemDecoration(GridDividerItemDecoration(spanCount = SPAN_COUNT))
    }

    override val fragment: Fragment
        get() = this
    override val pagerTag: String
        get() = "tag_arch_fragment"
    override val pagerName: String
        get() = "Arch"
    override val menuId: Int
        get() = R.id.arch

    companion object {
        const val SPAN_COUNT = 3
    }
}

