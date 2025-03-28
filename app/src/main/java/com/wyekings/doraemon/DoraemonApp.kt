package com.wyekings.doraemon

import android.os.Build
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.wyekings.common.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

/**
 *  @author wye on 6/8/23
 */
@HiltAndroidApp
class DoraemonApp : BaseApplication(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .crossfade(true)
            .build()
    }
}