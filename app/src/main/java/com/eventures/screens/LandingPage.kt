package com.eventures.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.eventures.viewmodels.LoginViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LandingPage(
    loginViewModel: LoginViewModel
) {

    val state = rememberPagerState(
        initialPage = 1,
        pageCount = { 3 }
    )

    HorizontalPager(
        modifier = Modifier
        ,
        state = state
    ) { page ->
        Box(
            modifier = Modifier
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "http://10.0.2.2:8000/p${page + 4}.jpg"),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            )
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Login")
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Signing in")
            }
        }
    }
}