package com.wyekings.doraemon.ui.main.domain.model

import android.app.Activity

/**
 *  @author wangpt on 9/4/23
 */
data class Module(
    val name:String,
    val clazz:Class<out Activity>
)
