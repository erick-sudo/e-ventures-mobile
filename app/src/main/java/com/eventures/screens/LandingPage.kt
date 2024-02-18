package com.eventures.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventures.R
import com.eventures.extensions.applyAlpha
import com.eventures.viewmodels.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LandingPage(
    loginViewModel: LoginViewModel
) {

    val drawableIds = listOf(
        R.drawable.land1 to "Welcome to EVentures!",
        R.drawable.land2 to "Explore personalized loan options tailored just for you.",
        R.drawable.land3 to "Apply for loans with competitive interest rates and flexible repayment plans."
    )

    val state = rememberPagerState(
        initialPage = 1,
        pageCount = { drawableIds.size }
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            state.animateScrollToPage((state.currentPage + 1) % state.pageCount)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier,
            state = state
        ) { page ->
            Image(
                painter = painterResource(id = drawableIds[page].first),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary.applyAlpha(0.3f)),
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(0.6f),
                    text = drawableIds[state.currentPage].second,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        drawableIds.forEach { _ ->
                            Box(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(shape = RoundedCornerShape(50))
                                    .background(color = MaterialTheme.colorScheme.primary)
                                    .size(15.dp)
                            )
                        }
                    }

                    Button(
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(20.dp),
                        contentPadding = PaddingValues(horizontal = 35.dp, vertical = 20.dp),
                        onClick = { loginViewModel.changeLoginStatus(-1) }
                    ) {
                        Text(text = "Login")
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Signing in")
                    }
                }
            }
        }
    }
}