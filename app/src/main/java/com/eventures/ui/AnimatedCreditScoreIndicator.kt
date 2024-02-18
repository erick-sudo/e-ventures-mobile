package com.eventures.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.eventures.R

@Composable
fun AnimatedCreditScoreIndicator(
    modifier: Modifier = Modifier,
    height: Dp = 20.dp,
    creditScore: Float
) {

    val creditScoreIndicators = listOf(
        colorResource(id = R.color.stop),
        colorResource(id = R.color.amber),
        colorResource(id = R.color.go)
    )

    var animatedCreditScore by remember {
        mutableFloatStateOf(0f)
    }

    val targetIndicator = when {
        (animatedCreditScore in 0.0 ..< 3.5) -> creditScoreIndicators[0]
        (animatedCreditScore in 3.5 ..< 7.5) -> creditScoreIndicators[1]
        else -> creditScoreIndicators[2]
    }

    val animatedIndicatorColor by animateColorAsState(
        targetValue = targetIndicator,
        label = "Animated Credit Score Indicator",
        animationSpec = tween(4000)
    )

    ProgressBar(
        modifier = Modifier
            .then(modifier),
        height = height,
        percentage = (creditScore/10) * 100,
        range = 0..10,
        colors = ProgressBarColors(
            barColor = animatedIndicatorColor,
            outlineColor = animatedIndicatorColor,
            containerColor = MaterialTheme.colorScheme.background
        )
    ) { score ->
        animatedCreditScore = score
        String.format("%.2f", score)
    }
}