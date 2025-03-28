package com.wyekings.component.sunflower

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import com.wyekings.common.base.BaseActivity
import com.wyekings.base.ext.start
import com.wyekings.component.sunflower.ui.SunflowerApp
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author wye on 9/5/23
 */
@AndroidEntryPoint
class SunflowerActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SunflowerApp()
        }
    }

    companion object {
        fun start(context: Context) {
            context.start<SunflowerActivity>()
        }
    }
}