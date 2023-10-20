package com.wyekings.uikit.drawerlayout

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

/**
 *  @author wye on 9/29/21
 */
private typealias OnDrawerSlide = (View, Float) -> Unit
private typealias OnDrawerOpened = (View) -> Unit
private typealias OnDrawerClosed = (View) -> Unit
private typealias OnDrawerStateChanged = (Int) -> Unit

fun DrawerLayout.addDrawerListener(drawerListener: (DrawerListenerImpl.() -> Unit)) {
    addDrawerListener(DrawerListenerImpl().also(drawerListener))
}

class DrawerListenerImpl : DrawerLayout.DrawerListener {

    private var onDrawerSlide: OnDrawerSlide? = null
    private var onDrawerOpened: OnDrawerOpened? = null
    private var onDrawerClosed: OnDrawerClosed? = null
    private var onDrawerStateChanged: OnDrawerStateChanged? = null

    fun onDrawerSlide(onDrawerSlide: OnDrawerSlide) {
        this.onDrawerSlide = onDrawerSlide
    }

    fun onDrawerOpened(onDrawerOpened: OnDrawerOpened) {
        this.onDrawerOpened = onDrawerOpened
    }

    fun onDrawerClose(onDrawerClosed: OnDrawerClosed) {
        this.onDrawerClosed = onDrawerClosed
    }

    fun onDrawerStateChanged(onDrawerStateChanged: OnDrawerStateChanged) {
        this.onDrawerStateChanged = onDrawerStateChanged
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        onDrawerSlide?.invoke(drawerView, slideOffset)
    }

    override fun onDrawerOpened(drawerView: View) {
        onDrawerOpened?.invoke(drawerView)
    }

    override fun onDrawerClosed(drawerView: View) {
        onDrawerClosed?.invoke(drawerView)
    }

    override fun onDrawerStateChanged(newState: Int) {
        onDrawerStateChanged?.invoke(newState)
    }
}