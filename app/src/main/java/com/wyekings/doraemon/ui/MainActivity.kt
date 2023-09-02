package com.wyekings.doraemon.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.wyekings.doraemon.base.BaseComposeActivity
import com.wyekings.compose.image.ImageSample
import com.wyekings.doraemon.ui.theme.DoraemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoraemonTheme {
                Content()
            }
        }
    }
}

@Composable
private fun Content() {
    ImageSample()
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
    Content()
}