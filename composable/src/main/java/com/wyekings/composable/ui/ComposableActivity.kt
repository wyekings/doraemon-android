package com.wyekings.composable.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.wyekings.base.BaseActivity
import com.wyekings.base.ext.start
import com.wyekings.composable.image.ImageSample
import com.wyekings.composable.tabrow.TabRowTemplate
import dagger.hilt.android.AndroidEntryPoint

/**
 *  @author wye on 9/3/23
 */
@AndroidEntryPoint
class ComposableActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TabRowTemplate()
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.start<ComposableActivity>()
        }
    }
}
