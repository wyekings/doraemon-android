package com.wyekings.logger.timber

import timber.log.Timber

internal class TimberReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // ignore
    }
}