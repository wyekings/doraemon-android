package com.wyekings.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *  @author wangpt on 9/5/23
 */
abstract class BaseFragment(@LayoutRes contentLayoutId:Int):Fragment(contentLayoutId) {
    constructor():this(0)
}