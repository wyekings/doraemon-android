package com.wyekings.doraemon.ui.composable.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.wyekings.doraemon.R
import com.wyekings.doraemon.api.TestUrls
import com.wyekings.doraemon.ui.theme.DoraemonTheme

/**
 *  @author wye on 8/9/23
 */

@Composable
fun ImageSample() {
    DoraemonTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(128.dp)
                SimpleImageSample(modifier)
                Divider(thickness = 0.5.dp)
                AsyncImageSample(modifier)
                Divider(thickness = 0.5.dp)
                SubcomposeAsyncImageSample(modifier)
                Divider(thickness = 0.5.dp)
                AsyncImagePainterSample(modifier = modifier)
                Divider(thickness = 0.5.dp)
                GifImageSample(modifier = modifier)
                Divider(thickness = 0.5.dp)
                CoilImageSample(modifier = modifier)
            }
        }
    }
}

@Composable
fun SimpleImageSample(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_tree),
        contentDescription = "Tree",
        modifier = modifier,
    )
}

@Composable
fun AsyncImageSample(modifier: Modifier) {
    AsyncImage(
//        model = ImageUrls.imageUrls[0],
        model = ImageRequest.Builder(LocalContext.current)
            .data(TestUrls.randomImage())
            .crossfade(true)
            .build(),
        contentDescription = "AsyncImage",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.ic_img_placeholder),
        modifier = modifier,
    )
}

@Composable
fun SubcomposeAsyncImageSample(modifier: Modifier) {
    SubcomposeAsyncImage(
        model = TestUrls.randomImage(),
        contentDescription = "SubcomposeAsyncImage",
        contentScale = ContentScale.Crop,
//        loading = {
//            CircularProgressIndicator(modifier = Modifier.size(4.dp))
//        },
        modifier = modifier
    ) {
        val state = painter.state
        if (state is State.Loading || state is State.Error) {
            CircularProgressIndicator()
        } else {
            SubcomposeAsyncImageContent()
        }
    }
}

@Composable
fun AsyncImagePainterSample(modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(model = TestUrls.randomImage()),
        contentDescription = "AsyncImagePainterSample",
        modifier = modifier,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun GifImageSample(modifier: Modifier) {
    AsyncImage(
        model = TestUrls.randomGif(),
        contentDescription = "AsyncImage",
        contentScale = ContentScale.Crop,
        placeholder = painterResource(id = R.drawable.ic_img_placeholder),
        modifier = modifier,
    )
}

@Composable
fun CoilImageSample(modifier: Modifier) {
    CoilImage(
        imageModel = { TestUrls.randomImage() },
        imageOptions = ImageOptions(
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun ImageSamplePreview() {
    ImageSample()
}
