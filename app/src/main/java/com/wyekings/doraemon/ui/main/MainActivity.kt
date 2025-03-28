package com.wyekings.doraemon.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import by.kirich1409.viewbindingdelegate.viewBinding
import com.wyekings.base.ext.start
import com.wyekings.common.base.BaseActivity
import com.wyekings.doraemon.R
import com.wyekings.doraemon.databinding.ActivityMainBinding
import com.wyekings.doraemon.databinding.DrawerMainHeaderBinding
import com.wyekings.uikit.drawerlayout.addDrawerListener
import com.wyekings.uikit.insets.insetter.applyStatusBarInsets
import com.wyekings.uikit.pager.Pager
import com.wyekings.uikit.pager.bind
import com.wyekings.uikit.pager.selectPager
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {

    @Inject @Named(TAG_PAGERS) lateinit var pagers: Array<Pager>

    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private val headViewBinding by viewBinding {
//        DrawerMainHeaderBinding.bind(viewBinding.navView.getHeaderView(0))
        DrawerMainHeaderBinding.bind(viewBinding.drawer.root)
    }
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen().apply { setKeepOnScreenCondition { true } }
        super.onCreate(savedInstanceState)
        applyEdgeToEdge()
        bindUi()
        hideSplashScreen(splashScreen)
    }

    private fun applyEdgeToEdge() {
        // take over WindowInsets from decor view
//        val avatarInitialTop = headViewBinding.ivAvatar.initialMargins.top
//        InsetterDelegate(this).apply {
//            val top = it.statusBarHeight
//            viewBinding.toolbar.updateMargins(top = top)
//            headViewBinding.ivAvatar.updateMargins(top = top + avatarInitialTop)
//            val bottom = it.navigationBarHeight
//            viewBinding.bottomNavigation.updatePadding(bottom = bottom)
//        }

        with(viewBinding) {
            toolbar.applyStatusBarInsets(applyByMargin = true)
            headViewBinding.ivAvatar.applyStatusBarInsets(applyByMargin = true)

            // BottomNavigationView handled WindowInsets by itself
            // bottomNavigation.applyNavigationBarInsets()
            // navView.applyStatusBarInsets(applyByMargin = false)
        }
    }

    private fun hideSplashScreen(splashScreen: SplashScreen) {
        viewBinding.root.post { splashScreen.setKeepOnScreenCondition { false } }
        window.decorView
    }

    private fun bindUi() {
        with(viewBinding) {
            bindToolbarWithDrawer()
            bottomNavigation.bind(
                R.menu.menu_bottom_nav_main,
                pagers,
                defaultIndex = viewModel.tabIndex,
                onSelected = { pager, index ->
                    toolbar.title = pager.pagerName
                    selectPager(index)
                },
            )
        }
    }

    private fun ActivityMainBinding.bindToolbarWithDrawer() {
        val drawerToggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            toolbar,
            R.string.action_open_drawer,
            R.string.action_close_drawer,
        )
        val arrowDrawable = drawerToggle.drawerArrowDrawable.apply {
            color = ContextCompat.getColor(this@MainActivity, R.color.black)
        }
        toolbar.navigationIcon = arrowDrawable
        drawerLayout.addDrawerListener(drawerToggle)
        drawerLayout.addDrawerListener {
            onDrawerOpened {
                Timber.d("onDrawerOpened")
            }

            onDrawerClose {
                Timber.d("onDrawerClosed")
            }
        }
    }

    private fun selectPager(index: Int) {
        viewModel.select(index)
        selectPager(R.id.container, index, pagers)
    }

    companion object {

        const val TAG_PAGERS = "main_activity_pagers"
        fun start(context: Context) {
            context.start<MainActivity> {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
        }
    }
}