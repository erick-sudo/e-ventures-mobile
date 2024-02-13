package com.eventures.models

import androidx.compose.ui.graphics.Color

enum class LoanStatus(
    private var status: String
) {
    Pending("Pending"),
    Approved("Approved"),
    Waiting("Waiting"),
    Disbursing("Disbursed"),
    FailedDisbursement("FailedDisbursement"),
    FullyDisbursed("FullyDisbursed"),
    Closed("Closed");

    override fun toString(): String = status

    fun indicator(): Color = when(this) {
        Pending -> Color.Blue
        Approved -> Color.Magenta
        Waiting -> Color.Yellow
        Disbursing -> Color.Green
        FailedDisbursement -> Color.Red
        FullyDisbursed -> Color.Cyan
        Closed -> Color.DarkGray
    }
}