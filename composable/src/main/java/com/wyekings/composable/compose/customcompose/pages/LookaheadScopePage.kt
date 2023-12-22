package com.wyekings.composable.compose.customcompose.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LookaheadScope
import androidx.compose.ui.layout.intermediateLayout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.sp
import com.wyekings.common.R
import kotlinx.coroutines.launch
import timber.log.Timber

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LookaheadScopePage() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        var expanded by remember { mutableStateOf(false) }
        LookaheadScope {
            val avatar = remember {
                movableContentOf {
                    Image(painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = "avatar",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .offset {
                                if (expanded) IntOffset(
                                    100.dp.roundToPx(), 100.dp.roundToPx()
                                ) else IntOffset.Zero
                            }
                            .animateSizeAndPlacement(this@LookaheadScope)
                            .padding(start = 10.dp, top = 10.dp)
                            .size(if (expanded) 120.dp else 80.dp)
                            .clip(CircleShape)
                            .clickable {
                                expanded = !expanded
                            })
                }
            }
            Row {
                if (!expanded) {
                    avatar()
                    Column {
                        Text(
                            text = "Joker",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 10.dp, top = 20.dp)
                        )

                        Text(
                            text = "Which Real-Life Comedian Inspired 'Joker'?",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                        )
                    }
                } else {
                    avatar()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.animateSizeAndPlacement(lookaheadScope: LookaheadScope) = composed {
    val offsetAnimation = remember { Animatable(IntOffset.Zero, IntOffset.VectorConverter) }
    val sizeAnimation = remember { Animatable(IntSize.Zero, IntSize.VectorConverter) }

    this.intermediateLayout { measurable, originalConstraints ->
        val constraints =
            if (sizeAnimation.value == IntSize.Zero) originalConstraints else Constraints.fixed(
                sizeAnimation.value.width, sizeAnimation.value.height
            )
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
                val target = with(lookaheadScope) {
                    lookaheadScopeCoordinates.localLookaheadPositionOf(coordinates).round()
                }
                if (target != offsetAnimation.targetValue) {
                    launch {
                        offsetAnimation.animateTo(target)
                    }
                }

                val lookaheadSize = lookaheadSize
                if (lookaheadSize != sizeAnimation.targetValue) {
                    launch {
                        sizeAnimation.animateTo(lookaheadSize)
                    }
                }
                val placementOffset =
                    lookaheadScopeCoordinates.localPositionOf(coordinates, Offset.Zero).round()
                val (x, y) = offsetAnimation.value - placementOffset
                placeable.placeRelative(x, y)
            } else {
                placeable.placeRelative(0, 0)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.clickToExpand() = composed {
    LookaheadScope {


    }
    var expanded by remember { mutableStateOf(false) }
    var height by remember { mutableIntStateOf(0) }
    val heightAnimate = animateIntAsState(targetValue = height, label = "height")
    this
        .intermediateLayout { measurable, constraints ->
            // get lookahead size
            height = lookaheadSize.height
            val placeable =
                measurable.measure(Constraints.fixed(lookaheadSize.width, heightAnimate.value))
            layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
            }
        }
        .then(if (expanded) height(100.dp) else Modifier)
        .clickable {
            expanded = !expanded
        }
}

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.clickToExpand1(lookaheadScope: LookaheadScope) = composed {
    LookaheadScope {


    }
    var expanded by remember { mutableStateOf(false) }
    var height by remember { mutableIntStateOf(0) }
    val heightAnimate = animateIntAsState(targetValue = height, label = "height")
    this
        .intermediateLayout { measurable, constraints ->
            // get lookahead size
            height = lookaheadSize.height
            val placeable =
                measurable.measure(Constraints.fixed(lookaheadSize.width, heightAnimate.value))
            layout(placeable.width, placeable.height) {
                placeable.placeRelative(0, 0)
            }
        }
        .then(if (expanded) height(100.dp) else Modifier)
        .clickable {
            expanded = !expanded
        }
}

// 3 -> 1
// 3 -> 2 -> 1
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.testIntermediateLayout() = this
    .layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        Timber.d("layout1----------->")
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }
    .intermediateLayout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        Timber.d("layout2----------->")
        layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
//                with(lookaheadScope) {

                lookaheadScopeCoordinates.localPositionOf(coordinates, Offset.Zero)
                val offset = lookaheadScopeCoordinates.localLookaheadPositionOf(coordinates)
                Timber.d("offset=$offset")
//                }
            }

            placeable.placeRelative(0, 0)
        }
    }
    .layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        Timber.d("layout3----------->")
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.lookahead(lookaheadScope: LookaheadScope) = this
    .layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }
    .intermediateLayout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            val coordinates = coordinates
            if (coordinates != null) {
                with(lookaheadScope) {
                    lookaheadScopeCoordinates.localPositionOf(coordinates, Offset.Zero)
                }
            }
            placeable.placeRelative(0, 0)
        }
    }
    .layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)
        }
    }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Modifier.transform(lookaheadScope: LookaheadScope) = composed {
    var targetOffset = remember { mutableStateOf(Offset.Zero) }
    this.intermediateLayout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        layout(placeable.width, placeable.height) {
            val coordinates = coordinates

            placeable.placeRelative(0, 0)
        }
    }
}


@Preview
@Composable
private fun LookaheadScopePagePreview() {
    LookaheadScopePage()
}