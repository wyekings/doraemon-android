package com.wyekings.doraemon.ui.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.wyekings.doraemon.ui.MainActivity
import com.wyekings.doraemon.ui.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  @author wangpt on 9/2/23
 */
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity(){

    @Inject lateinit var navigator:Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        super.onCreate(savedInstanceState)
        if (isNotRootLauncher()){
            finish()
            return
        }

        lifecycleScope.launch {
            delay(500)
            // show ads here
            // splashScreen.setKeepOnScreenCondition { false }

            navigator.toMain(this@SplashActivity)
            finish()
        }
    }

    private fun isNotRootLauncher(): Boolean {
        if (!this.isTaskRoot && intent != null) {
            val action = intent.action
            return intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN == action
        }
        return false
    }

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, SplashActivity::class.java)
            context.startActivity(intent)
        }
    }
}