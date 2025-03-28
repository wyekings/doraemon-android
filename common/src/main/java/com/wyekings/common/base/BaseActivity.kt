package com.wyekings.common.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *  @author wye on 9/3/23
 */
abstract class BaseActivity(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {
    constructor() : this(0)

   protected open val enableEdgeToEdge: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        if (enableEdgeToEdge) enableEdgeToEdge()
        super.onCreate(savedInstanceState)
    }
}