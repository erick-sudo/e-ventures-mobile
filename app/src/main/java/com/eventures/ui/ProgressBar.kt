package com.eventures.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    colors: ProgressBarColors = ProgressBarColors(),
    percentage: Float = 0f,
    height: Dp = 20.dp,
    range: IntRange = 0..100,
    valueFormatter: (Float) -> String = { labelValue ->
        String.format("%.2f", labelValue)
    }
) {
    val textMeasurer = rememberTextMeasurer()

    var startProgress by remember {
        mutableStateOf(false)
    }

    var showTextInline by remember {
        mutableStateOf(false)
    }

    val animatedFinalValue by animateFloatAsState(
        targetValue = if(startProgress) ((percentage/100) * (range.last - range.first)) + range.first else 0f,
        label = "FinalValue",
        animationSpec = tween(4000)
    )

    val text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        ) {
            append(valueFormatter(animatedFinalValue))
        }
    }

    val (textWidth, textHeight) = textMeasurer.measure(text).size

    LaunchedEffect(Unit) {
        startProgress = true
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(height)
                .clip(RoundedCornerShape(50))
                .background(colors.containerColor)
        ) {
            val (w, h) = size

            drawRoundRect(
                color = colors.outlineColor,
                size = Size(
                    width = w,
                    height = h
                ),
                style = Stroke(width = 5.dp.toPx()),
                cornerRadius = CornerRadius((h/2).dp.toPx())
            )

            val progressWidth = (animatedFinalValue/(range.last - range.first)) * w

            drawRoundRect(
                color = colors.barColor,
                size = Size(
                    width = progressWidth,
                    height = h
                ),
                cornerRadius = CornerRadius((h/2).dp.toPx())
            )

            if(progressWidth > (textWidth * 1.1)) {
                showTextInline = false
                drawText(
                    textMeasurer,
                    text,
                    style = TextStyle(
                        color = colors.textColor
                    ),
                    topLeft = Offset(
                        progressWidth/2 - textWidth/2,
                        h/2 - textHeight/2
                    )
                )
            } else {
                showTextInline = true
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Start),
            visible = showTextInline
        ) {
            Text(
                text = text
            )
        }
    }
}

data class ProgressBarColors(
    val textColor: Color = Color.Black,
    val outlineColor: Color = Color.White,
    val containerColor: Color = Color.Black,
    val barColor: Color = Color.White
)

@Preview(showSystemUi = false)
@Composable
fun ShadedProgressBarPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(500.dp)
            .fillMaxWidth()
    ) {
        ProgressBar(
            modifier = Modifier
                .width(500.dp),
            percentage = 40f,
            height = 40.dp
        ) { percentage ->
            "${percentage.roundToInt()}%"
        }
    }
}