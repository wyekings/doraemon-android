package com.wyekings.composable.compose.modifier

import androidx.compose.ui.layout.OnRemeasuredModifier
import androidx.lifecycle.ViewModel
import com.wyekings.composable.compose.modifier.pages.ComposedModifierPage
import com.wyekings.composable.compose.modifier.pages.DrawModifierPage
import com.wyekings.composable.compose.modifier.pages.LayoutModifierPage
import com.wyekings.composable.compose.modifier.pages.ModifierLocalPage
import com.wyekings.composable.compose.modifier.pages.OnRemeasuredModifierPage
import com.wyekings.composable.compose.modifier.pages.ParentDataModifierPage
import com.wyekings.composable.compose.modifier.pages.PointerInputModifierPage
import com.wyekings.composable.compose.modifier.pages.SemanticsModifierPage
import com.wyekings.composable.ui.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ModifierViewModel @Inject constructor() : ViewModel() {
    val pages = listOf(
        Page(title = "ComposedModifier", content = { ComposedModifierPage() }),
        Page(title = "LayoutModifier", content = { LayoutModifierPage() }),
        Page(title = "DrawModifier", content = { DrawModifierPage() }),
        Page(title = "PointerInputModifier", content = { PointerInputModifierPage() }),
        Page(title = "ParentDataModifier", content = { ParentDataModifierPage() }),
        Page(title = "SemanticsModifier", content = { SemanticsModifierPage() }),
        Page(title = "OnRemeasuredModifier", content = { OnRemeasuredModifierPage() }),
        Page(title = "ModifierLocal", content = { ModifierLocalPage() }),
    ).reversed()
}