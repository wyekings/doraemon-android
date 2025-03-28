package com.wyekings.components.ui.insets

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.common.base.BaseActivity
import com.wyekings.components.R
import com.wyekings.components.databinding.ActivityWindowInsetsBinding
import com.wyekings.uikit.insets.insetter.applyImeInsets
import com.wyekings.uikit.insets.insetter.applyStatusBarInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WindowInsetsActivity : BaseActivity(R.layout.activity_window_insets) {

    private val viewBinding by viewBinding(ActivityWindowInsetsBinding::bind)
    private val viewModel by viewModels<WindowInsetsViewModel>()
    private val conversationAdapter = ConversionAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyEdgeToEdge()
        bindUi()
        collectUi()
    }

    private fun applyEdgeToEdge() {
//        with(viewBinding) {
//            val initialBottom = etMessage.initialMargins.bottom
//            InsetterDelegate(this@WindowInsetsActivity).apply {
//                val top = it.getInsets(WindowInsetsCompat.Type.statusBars()).top
//                toolbar.updateMargins(top = top)
//
////                val bottom = it.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
////                val ime = it.getInsets(WindowInsetsCompat.Type.ime()).bottom
////                etMessage.updateMargins(bottom = initialBottom + if (ime > 0) ime else bottom)
//            }
//        }

        with(viewBinding) {
            topBar.applyStatusBarInsets(applyByMargin = false)
            bottomBar.applyImeInsets(viewBinding.rvMessage)
        }
    }

    private fun bindUi() {
        with(viewBinding) {
            toolbar.title = "WindowInsets"
            rvMessage.apply {
                layoutManager = LinearLayoutManager(
                    this@WindowInsetsActivity, LinearLayoutManager.VERTICAL, true
                )
                adapter = conversationAdapter
            }
        }
    }

    private fun collectUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.map { it.conversions }.distinctUntilChanged().collectLatest {
                    conversationAdapter.submitList(it)
                }
            }
        }
    }
}