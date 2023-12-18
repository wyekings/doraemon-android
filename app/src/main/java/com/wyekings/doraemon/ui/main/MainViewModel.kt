package com.wyekings.doraemon.ui.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *  @author wye on 9/5/23
 */

private const val TAB_INDEX = "tab_index"

@HiltViewModel
class MainViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var tabIndex
        private set(value) = savedStateHandle.set(TAB_INDEX, value)
        get() = savedStateHandle[TAB_INDEX] ?: 0

    fun select(index: Int) {
        this.tabIndex = index
    }
}