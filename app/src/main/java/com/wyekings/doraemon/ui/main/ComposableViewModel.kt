package com.wyekings.doraemon.ui.main

import androidx.lifecycle.ViewModel
import com.wyekings.composable.ui.ComposableActivity
import com.wyekings.doraemon.ui.main.domain.model.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */

data class ComposableUiState(
    val modules: List<Module> = emptyList()
)

@HiltViewModel
class ComposableViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ComposableUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val modules = listOf(Module("Composable", ComposableActivity::class.java))
        _uiState.update { it.copy(modules = modules) }
    }

}