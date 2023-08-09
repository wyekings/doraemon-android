package com.wyekings.doraemon

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wyekings.doraemon.base.BaseComposeActivity
import com.wyekings.doraemon.ui.theme.DoraemonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    Content()
}

@Composable
private fun Content() {
    DoraemonTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageSample()
                Divider(thickness = 0.5.dp)
                TextSample(name = "Test")
            }
        }
    }
}

@Composable
fun ImageSample() {
    Image(
        painter = painterResource(id = R.drawable.ic_tree),
        contentDescription = "Tree",
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .size(128.dp),
    )
}

@Composable
fun TextSample(name: String, modifier: Modifier = Modifier) {
    Box {
        Text(
            text = "Hello $name!", modifier = modifier
                .padding(8.dp)
                .background(Color.Yellow)
                .clickable {  }
                .padding(8.dp)
        )
    }
}