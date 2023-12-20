package com.wyekings.composable.compose.sideeffects.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ProduceStatePage() {
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ProduceFlowState()
        Spacer(modifier = Modifier.height(50.dp))
        ProduceLiveDataState()
        Spacer(modifier = Modifier.height(50.dp))
        ProduceRxState()
        Spacer(modifier = Modifier.height(50.dp))
        SuspendState()
    }
}

@Composable
private fun SuspendState() {
    val suspendState = produceState(initialValue = "") {
        value = getValue()

        // suspend function
        awaitDispose {

        }
    }
    Text(text = "produceState=${suspendState.value}")
}

suspend fun getValue(): String {
    delay(1000)
    return "suspend"
}

@Composable
private fun ProduceRxState() {
    val rx = remember {
        BehaviorSubject.createDefault(0)
    }

    val rxState = rx.subscribeAsState(initial = rx.value)
    Button(onClick = {
        rx.onNext(rx.value?.plus(1) ?: 0)
    }) {
        Text(text = "rx=${rxState.value}")
    }
}

@Composable
private fun ProduceLiveDataState() {
    val liveData = remember {
        MutableLiveData(0)
    }


    val liveDataState = liveData.observeAsState()
    Button(onClick = {
        liveData.value = liveData.value?.plus(1)
    }) {
        Text(text = "livedata=${liveDataState.value}")
    }
}

@Composable
private fun ProduceFlowState() {
    val flow = remember {
        MutableStateFlow(0)
    }
    val flowState = flow.collectAsState()

    Button(onClick = {
        flow.value += 1
    }) {
        Text(text = "flow=${flowState.value}")
    }
}

@Composable
private fun <T> LiveData<T>.observeAsState(): State<T?> {
    val lifecycleOwner = LocalLifecycleOwner.current
    return produceState(initialValue = value) {
        val observer = Observer<T> {
            value = it
        }
        observe(lifecycleOwner, observer)
        awaitDispose {
            removeObserver(observer)
        }
    }
}

@Composable
private fun <T> BehaviorSubject<T>.subscribeAsState(initial: T): State<T> = produceState(
    initialValue = value!!
) {
    val disposable = subscribe {
        value = it!!
    }
    awaitDispose {
        disposable.dispose()
    }
}


@Preview
@Composable
private fun ProduceStatePagePreview() {
    ProduceStatePage()
}