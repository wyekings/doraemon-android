package com.wyekings.playground

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wyekings.base.BaseActivity
import com.wyekings.base.ext.start
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 *  @author wye on 9/8/23
 */
@AndroidEntryPoint
class PlaygroundActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            TabRowTemplate()
//            var name by remember {
//                mutableStateOf("Init")
//            }
//
//            Button(onClick = { /*TODO*/ }) {
//                Text(
//                    text = name, modifier = Modifier
//                        .statusBarsPadding()
//                )
//            }
//
//            LaunchedEffect(Unit) {
//                delay(2000)
//                name = "Changed"
//            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.start<PlaygroundActivity>()
        }
    }
}

@Composable
fun ShowChaCount(value: String) {

    // Text(text = "value length=${value.length}")

    // 被重组时会反复计算 value.length，但 value.length 可能不变
    val length by remember(value) { mutableIntStateOf(value.length) }
    Text(text = "value length=${length}")
}