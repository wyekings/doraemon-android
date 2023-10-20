package com.wyekings.doraemon.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */
@HiltViewModel
class MainViewModel @Inject constructor():ViewModel() {

     var tabIndex = 0
         private set

    fun select(index: Int) {
        this.tabIndex = index
    }
}