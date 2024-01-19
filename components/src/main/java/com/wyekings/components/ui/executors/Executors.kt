package com.wyekings.components.ui.executors

import org.jetbrains.annotations.TestOnly
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@TestOnly
fun executorsTest() {
    val command = Runnable {
        Timber.d("command run!")
    }
    val fixedThreadPool = Executors.newFixedThreadPool(5)
    fixedThreadPool.execute(command)

    val cachedThreadPool = Executors.newCachedThreadPool()
    cachedThreadPool.execute(command)

    val scheduledThreadPool = Executors.newScheduledThreadPool(5)
    scheduledThreadPool.schedule(command, 1, TimeUnit.SECONDS)

    val singleThreadExecutor = Executors.newSingleThreadExecutor()
    singleThreadExecutor.execute(command)
}