package com.wyekings.doraemon.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.wyekings.doraemon.base.BaseComposeActivity
import com.wyekings.doraemon.ui.compose.sample.ImageSample
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
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