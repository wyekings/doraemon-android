package com.wyekings.doraemon.ui.main

import androidx.lifecycle.ViewModel
import com.wyekings.components.ui.customview.CustomViewActivity
import com.wyekings.components.ui.insets.WindowInsetsActivity
import com.wyekings.doraemon.ui.main.domain.model.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */

data class ComponentsUiState(
    val modules: List<Module> = emptyList()
)

@HiltViewModel
class ComponentsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ComponentsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val modules = listOf(
            Module("WindowInsets", WindowInsetsActivity::class.java),
            Module("CustomView", CustomViewActivity::class.java),
        )
        _uiState.update { it.copy(modules = modules) }
    }

}