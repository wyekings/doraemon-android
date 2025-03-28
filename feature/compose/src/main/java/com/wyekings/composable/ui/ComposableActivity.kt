package com.wyekings.composable.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import com.wyekings.common.base.BaseActivity
import com.wyekings.base.ext.start
import com.wyekings.common.ui.theme.DoraemonTheme
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
        }
    }

    companion object {
        fun start(context: Context) {
            context.start<ComposableActivity>()
        }
    }
}
