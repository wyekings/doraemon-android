package com.wyekings.doraemon.ui.main

import androidx.lifecycle.ViewModel
import com.wyekings.component.playground.PlaygroundActivity
import com.wyekings.component.sunflower.SunflowerActivity
import com.wyekings.doraemon.ui.main.domain.model.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */

data class ArchUiState(
    val modules: List<Module> = emptyList()
)

@HiltViewModel
class ArchViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ArchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val modules = listOf(
            Module("Sunflower", SunflowerActivity::class.java),
            Module("Playground", PlaygroundActivity::class.java),
        )
        _uiState.update { it.copy(modules = modules) }
    }

}