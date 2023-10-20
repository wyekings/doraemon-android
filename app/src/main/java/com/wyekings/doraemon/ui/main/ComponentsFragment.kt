package com.wyekings.doraemon.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.doraemon.R
import com.wyekings.base.BaseFragment
import com.wyekings.doraemon.databinding.FragmentComponentsBinding
import com.wyekings.base.ext.start
import com.wyekings.uikit.pager.Pager
import com.wyekings.uikit.recyclerview.GridDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */
@AndroidEntryPoint
class ComponentsFragment @Inject constructor() : BaseFragment(R.layout.fragment_components), Pager {

    @Inject lateinit var moduleAdapter: ModuleAdapter

    private val viewModel by viewModels<ComponentsViewModel>()
    private val viewBinding by viewBinding(FragmentComponentsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindUi()
        collectUi()
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

    private fun collectUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map { it.modules }.distinctUntilChanged().collectLatest {
                    moduleAdapter.submitList(it)
                }
            }
        }
    }

    override val fragment: Fragment
        get() = this
    override val pagerTag: String
        get() = "tag_components_fragment"

    override val pagerName: String
        get() = "Components"
    override val menuId: Int
        get() = R.id.components

    companion object {
        const val SPAN_COUNT = 3
    }
}

