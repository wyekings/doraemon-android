package com.wyekings.composable.compose.modifier

import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.modifier.pages.ComposedModifierPage
import com.wyekings.composable.compose.modifier.pages.LayoutModifierPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModifierViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page(title = "ComposedModifier", content = { ComposedModifierPage() }),
        Page(title = "LayoutModifier", content = { LayoutModifierPage() }),
    ).reversed()
}