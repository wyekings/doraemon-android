package com.wyekings.composable.compose.composable

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.composable.domain.Composable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeUiState(
    val composableList: List<Composable> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val composableList = mutableStateListOf(
        Composable("Text", "text"),
        Composable("Image", "image"),
        Composable("Button", "button"),
        Composable("TabRow", "tab_row"),
        Composable("Animatable", "animatable"),
        Composable("Bounce", "bounce"),
        Composable("Transition", "transition"),
        Composable("AnimatedVisibility", "animatedVisibility"),
        Composable("Animation", "animation"),
        Composable("Modifier", "modifier"),
    )
}