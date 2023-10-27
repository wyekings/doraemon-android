package com.wyekings.composable.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wyekings.base.BaseActivity
import com.wyekings.base.ext.start
import com.wyekings.common.ui.theme.DoraemonTheme
import com.wyekings.composable.compose.ripple.NoRippleContent
import com.wyekings.composable.compose.ripple.NoRippleTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author wye on 9/3/23
 */
@AndroidEntryPoint
class ComposableActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoraemonTheme {
                ComposableApp()
            }

//            Column {
//                CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
//                    Button(onClick = {
//
//                    }, modifier = Modifier.systemBarsPadding()) {
//                        Text("Button")
//                    }
//                }
//                NoRippleContent {
//                    Button(onClick = {
//
//                    }, modifier = Modifier.systemBarsPadding()) {
//                        Text("Button")
//                    }
//                }
//            }

//            var big by remember { mutableStateOf(false) }
//            val size = if (big) 128.dp else 48.dp
//            val anim = remember { Animatable(size, Dp.VectorConverter) }
//            LaunchedEffect(big) {
//                anim.snapTo(if (big) 256.dp else 0.dp)
//                anim.animateTo(size)
//            }
//
//            Box(modifier = Modifier
//                .systemBarsPadding()
//                .padding(start = 20.dp)
//                .size(anim.value)
//                .background(Color.Green)
//                .clickable {
//                    big = !big
//                })
//
        }
    }

    companion object {
        fun start(context: Context) {
            context.start<ComposableActivity>()
        }
    }
}
