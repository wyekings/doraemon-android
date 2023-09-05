package com.wyekings.base

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *  @author wye on 9/3/23
 */
abstract class BaseActivity(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {
    constructor() : this(0)
}