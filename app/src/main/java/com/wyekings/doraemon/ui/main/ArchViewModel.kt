package com.wyekings.doraemon.ui.main

import androidx.lifecycle.ViewModel
import com.wyekings.composable.ui.ComposableActivity
import com.wyekings.doraemon.ui.main.domain.model.Module
import com.wyekings.sunflower.ui.SunflowerActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 *  @author wangpt on 9/5/23
 */

data class ArchUiState(
    val modules: List<Module> = emptyList()
)

@HiltViewModel
class ArchViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ArchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val modules = listOf(Module("Sunflower", SunflowerActivity::class.java))
        _uiState.update { it.copy(modules = modules) }
    }

}