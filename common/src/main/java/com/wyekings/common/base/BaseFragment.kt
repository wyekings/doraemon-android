package com.wyekings.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *  @author wye on 9/5/23
 */
abstract class BaseFragment(@LayoutRes contentLayoutId:Int): Fragment(contentLayoutId) {
    constructor():this(0)
}