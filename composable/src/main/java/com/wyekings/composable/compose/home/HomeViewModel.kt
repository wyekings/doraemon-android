package com.wyekings.composable.compose.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.home.domain.Composable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class HomeUiState(
    val composableList: List<Composable> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    val composableList = mutableStateListOf(
        Composable("Animations", "animations"),
        Composable("Image", "image"),
        Composable("TabRow", "tab_row"),
    )

}