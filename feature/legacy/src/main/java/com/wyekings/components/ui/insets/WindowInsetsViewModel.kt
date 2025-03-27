package com.wyekings.components.ui.insets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class KeyboardTransitionUiState(
    val conversions: List<Conversion> = emptyList(),
)

@HiltViewModel
class WindowInsetsViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(KeyboardTransitionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val conversions = (0..30).map {
            Conversion(
                id = it, message = "To you $it"
            )
        }
        _uiState.update { it.copy(conversions = conversions) }
    }
}