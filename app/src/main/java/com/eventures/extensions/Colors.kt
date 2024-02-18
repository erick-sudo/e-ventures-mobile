package com.eventures.extensions

import androidx.compose.ui.graphics.Color

fun Color.applyAlpha(alpha: Float): Color {
    return Color(this.red, this.green, this.blue, alpha)
}