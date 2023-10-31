package com.wyekings.composable.compose.basic

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.wyekings.composable.compose.ripple.NoRippleTheme
import com.wyekings.composable.ui.TopBar
import com.wyekings.composeable.ripple.rememberNoRippleInteractionSource

@Composable
fun ButtonScreen() {
    Scaffold(
        topBar = {
            TopBar(title = "Button")
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val context = LocalContext.current

            Button(
                onClick = {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            ) {
                Text(text = "Button", color = Color.DarkGray)
            }

            Button(
                onClick = {
                    Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                interactionSource = rememberNoRippleInteractionSource(),
            ) {
                Text(text = "NoRipple by interactionSource", color = Color.DarkGray)
            }

            // Not working
//            CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
//                Button(
//                    onClick = {
//                        Toast.makeText(context, "Button clicked", Toast.LENGTH_LONG).show()
//                    }, colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow)
//                ) {
//                    Text(text = "NoRipple Button", color = Color.DarkGray)
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview() {
    ButtonScreen()
}