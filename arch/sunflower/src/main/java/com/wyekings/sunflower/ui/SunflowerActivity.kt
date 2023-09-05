package com.wyekings.sunflower.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import com.wyekings.base.BaseActivity
import com.wyekings.base.ext.start
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author wangpt on 9/5/23
 */
@AndroidEntryPoint
class SunflowerActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }

    companion object {
        fun start(context: Context) {
            context.start<SunflowerActivity>()
        }
    }
}