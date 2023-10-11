package com.wyekings.playground

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wyekings.base.BaseActivity
import com.wyekings.base.ext.start
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

/**
 *  @author wye on 9/8/23
 */
@AndroidEntryPoint
class PlaygroundActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var count by remember { mutableIntStateOf(0) }
            println("print 1")
            Column {
                println("2")
                Text(text = "$count", modifier = Modifier.clickable {
                    count++
                })
                Composable3()
                Composable4(title = count.toString())
            }
        }
    }

    @Composable
    fun Composable3() {
        println("3")
    }

    @Composable
    fun Composable4(title:String) {
        println("4")
        Text(text = title)
    }

    companion object {
        fun start(context: Context) {
            context.start<PlaygroundActivity>()
        }
    }
}

// 只是一个标记
// 如果能保证可变的公开属性改变的时候能够通知到调用法就行
// 传统使用下面的写法保证,被 compose 识别为可靠的类
// 主要给接口使用
// 嵌套子类也必须可靠
// 相等性的判断是等价不变的
// 第二条复合，就会判定为稳定

// 不使用可变属性 重写 equals,不适用 data class
// by mutableStatOf 代理
// 对于接口，虽然不符合compose 对稳定性的判断，但是子类保证了变更是会同步重组使用的地方，就可以强制认为 稳定
// @Immutable

// 不要轻易重写 equals
// 使用 by mutable
@Stable
class User(name: String) {
    // 公开属性
    // 如果全部这种写法，可以省去 @Stable，compose 会自动添加
    var name by mutableStateOf(name)
}