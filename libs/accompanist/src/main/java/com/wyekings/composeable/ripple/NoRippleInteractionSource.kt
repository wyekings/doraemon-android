package com.wyekings.composeable.ripple

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 *  @author wye on 9/7/23
 */
class NoRippleInteractionSource : MutableInteractionSource {
    override val interactions: Flow<Interaction>
        get() = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction): Boolean = true
}

@Composable
fun rememberNoRippleInteractionSource():MutableInteractionSource {
    return remember {
        NoRippleInteractionSource()
    }
}
