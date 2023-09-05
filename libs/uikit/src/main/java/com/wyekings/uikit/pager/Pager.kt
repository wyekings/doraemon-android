package com.wyekings.uikit.pager

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commitNow
import androidx.lifecycle.Lifecycle
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 *  @author wye on 4/24/23
 */
interface Pager {

    val fragment: Fragment

    val pagerTag: String

    val pagerName:String

    val menuId: Int
}

fun FragmentActivity.selectPager(@IdRes containerId: Int, index: Int, pagers: Array<Pager>) {
    selectPager(supportFragmentManager, containerId, index, pagers)
}

fun Fragment.selectPager(@IdRes containerId: Int, index: Int, pagers: Array<Pager>) {
    if (index > pagers.size - 1 || index < 0) return
    selectPager(childFragmentManager, containerId, index, pagers)
}

private fun selectPager(
    fragmentManager: FragmentManager,
    @IdRes containerId: Int,
    index: Int,
    pagers: Array<Pager>,
) {
    if (index > pagers.size - 1) return
    val pager = pagers[index]
    fragmentManager.commitNow(true) {
        val previousFragment = fragmentManager.primaryNavigationFragment
        previousFragment?.let {
            if (it.tag != pager.pagerTag) {
                hide(previousFragment)
                setMaxLifecycle(previousFragment, Lifecycle.State.STARTED)
            }
        }

        var currentFragment: Fragment? = fragmentManager.findFragmentByTag(pager.pagerTag)
        if (currentFragment == null) {
            currentFragment = pager.fragment
            add(containerId, currentFragment, pager.pagerTag)
            setReorderingAllowed(true)
        } else {
            show(currentFragment)
            setMaxLifecycle(currentFragment, Lifecycle.State.RESUMED)
        }
        setPrimaryNavigationFragment(currentFragment)
    }
}

inline fun BottomNavigationView.bind(
    menuResId: Int,
    pagers: Array<Pager>,
    defaultIndex: Int = 0,
    crossinline onSelected: (Pager,Int) -> Unit,
) {
    inflateMenu(menuResId)
//    itemIconTintList = null
    selectedItemId = pagers[defaultIndex].menuId
    onSelected.invoke(pagers[defaultIndex],defaultIndex)
    setOnItemSelectedListener { menu ->
        if (menu.isChecked) return@setOnItemSelectedListener false

        val index = pagers.indexOfFirst { it.menuId == menu.itemId }
        val exist = index != -1

        if (exist) onSelected.invoke(pagers[index],index)

        return@setOnItemSelectedListener exist
    }
    setOnItemReselectedListener {
        // ignore
    }
}
