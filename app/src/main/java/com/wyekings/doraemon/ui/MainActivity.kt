package com.wyekings.doraemon.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.wyekings.doraemon.base.BaseComposeActivity
import com.wyekings.compose.image.ImageSample
import com.wyekings.doraemon.ui.theme.DoraemonTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
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
