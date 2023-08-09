package com.wyekings.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *  @author wye on 8/8/23
 */
abstract class BaseAppCompatActivity(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId) {

    /**
     * Hilt doesn't support default value for main constructor, so just claim another constructor.
     */
    constructor() : this(0)
}