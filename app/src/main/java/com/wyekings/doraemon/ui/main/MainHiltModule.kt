package com.wyekings.doraemon.ui.main

import com.wyekings.uikit.pager.Pager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Named

/**
 *  @author wye on 9/5/23
 */
@Module
@InstallIn(ActivityComponent::class)
object MainHiltModule {

    @Provides
    @Named(MainActivity.TAG_PAGERS)
    fun provideMainPagers(
        arch:ArchFragment,
        components:ComponentsFragment,
        composable:ComposableFragment,
    ):Array<Pager> {
        return arrayOf(
            arch,
            components,
            composable
        )
    }
}