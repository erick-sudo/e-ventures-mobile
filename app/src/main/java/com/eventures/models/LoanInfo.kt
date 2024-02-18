package com.eventures.models

import com.google.gson.annotations.SerializedName
import java.time.temporal.ChronoUnit

data class LoanInfo(
    @SerializedName("id") val id: String,
    @SerializedName("principal_amount") val principalAmount: Float,
    @SerializedName("loan_duration") val loanDuration: Float,
    @SerializedName("rate_of_interest") val rateOfInterest: Float,
    @SerializedName("total_pay") val totalPay: Float,
    @SerializedName("repayment_interval") val repaymentInterval: ChronoUnit,
    @SerializedName("installment") val installment: Float,
    @SerializedName("days_elapsed") val daysElapsed: Int,
    @SerializedName("elapsed_periods") val elapsedPeriods: Int,
    @SerializedName("current_period") val currentPeriod: Float,
    @SerializedName("repaid_amount") val repaidAmount: Float,
    @SerializedName("balance") val balance: Float
)