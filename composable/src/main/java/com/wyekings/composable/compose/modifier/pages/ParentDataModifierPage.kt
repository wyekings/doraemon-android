package com.wyekings.composable.compose.modifier.pages

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.ParentDataModifierNode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ParentDataModifierPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Red)
                    .weight(1f),
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Green),
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Yellow),
            )
        }
        CustomLayout {
            Box(
                modifier = Modifier
                    .customWeight(weight = 1f)
                    .customFill(false)
                    .layoutId("outerBox")
            ) {
                Box(modifier = Modifier.size(10.dp))
            }
        }
    }
}

private data class CustomParentData(
    val weight: Float = 0f,
    val fill: Boolean = true,
)

private class CustomWeightNode(var weight: Float) : ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return ((parentData as? CustomParentData))?.copy(weight = weight)
            ?: CustomParentData(weight)
    }
}

private class CustomFillNode(var fill: Boolean) : ParentDataModifierNode, Modifier.Node() {
    override fun Density.modifyParentData(parentData: Any?): Any {
        return ((parentData as? CustomParentData))?.copy(fill = fill)
            ?: CustomParentData(fill = fill)
    }
}

private class CustomWeightElement(private val weight: Float) :
    ModifierNodeElement<CustomWeightNode>() {
    override fun create(): CustomWeightNode = CustomWeightNode(weight)

    override fun update(node: CustomWeightNode) {
        node.weight = weight
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? CustomWeightElement ?: return false
        return weight == otherModifier.weight
    }

    override fun hashCode(): Int {
        return 31 * weight.hashCode()
    }
}

private class CustomFillElement(private val fill: Boolean) : ModifierNodeElement<CustomFillNode>() {
    override fun create(): CustomFillNode = CustomFillNode(fill)

    override fun update(node: CustomFillNode) {
        node.fill = fill
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val otherModifier = other as? CustomFillElement ?: return false
        return fill == otherModifier.fill
    }

    override fun hashCode(): Int {
        return 31 * fill.hashCode()
    }
}

@LayoutScopeMarker
@Immutable
interface CustomScope {

    @Stable
    fun Modifier.customWeight(
        @FloatRange(from = 0.0, fromInclusive = false) weight: Float,
    ): Modifier

    fun Modifier.customFill(
        fill: Boolean = true,
    ): Modifier
}

private object CustomScopeInstance : CustomScope {

    override fun Modifier.customWeight(weight: Float): Modifier {
        return this then CustomWeightElement(weight)
    }

    override fun Modifier.customFill(fill: Boolean): Modifier {
        return this then CustomFillElement(fill = fill)
    }
}

@Composable
fun CustomLayout(modifier: Modifier = Modifier, content: @Composable CustomScope.() -> Unit) {
    Layout(
        content = { CustomScopeInstance.content() }, modifier = modifier
    ) { measurables, constraints ->
        val placeables = measurables.map {
            val parentData = it.parentData as? CustomParentData
            val weight = parentData?.weight
            val fill = parentData?.fill
            // todo with weight and fill

            val isOuterBox = it.layoutId == "outerBox"

            it.measure(constraints = constraints)
        }

        val width = placeables.sumOf { it.width }
        val height = placeables.sumOf { it.height }
        layout(width, height) {
            // todo
        }
    }
}