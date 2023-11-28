package com.wyekings.composeable.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noRippleClickable(
    interactionSource: MutableInteractionSource? = null, onClick: () -> Unit
): Modifier = composed {
    this then clickable(
        interactionSource = interactionSource ?: remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick,
    )
}
