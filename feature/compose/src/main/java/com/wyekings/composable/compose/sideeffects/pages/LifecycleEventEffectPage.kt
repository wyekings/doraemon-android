package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import timber.log.Timber

@Composable
fun LifecycleEventEffectPage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        LifecycleStartEffect(Unit) {
            Timber.d("LifecycleStartEffect")
            onStopOrDispose {

            }
        }

        LifecycleResumeEffect(Unit) {
            Timber.d("LifecycleResumeEffect")
            onPauseOrDispose {

            }
        }

        LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
            Timber.d("On_CREATE")
        }
        LifecycleEventEffect(event = Lifecycle.Event.ON_PAUSE) {
            Timber.d("ON_PAUSE")
        }
        LifecycleEventEffect(event = Lifecycle.Event.ON_STOP) {
            Timber.d("ON_STOP")
        }

        Text(text = "LifecycleEvent")
    }
}

@Preview
@Composable
private fun LifecycleEventEffectPagePreview() {
    LifecycleEventEffectPage()
}