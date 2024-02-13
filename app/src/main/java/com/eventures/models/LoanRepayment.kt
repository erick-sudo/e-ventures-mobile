package com.eventures.models

import com.google.gson.annotations.SerializedName

data class LoanRepayment(
    @SerializedName("id") var id: String = "",
    @SerializedName("loan_id") var loanId: String,
    @SerializedName("collection_date") var collectionDate: String,
    @SerializedName("amount") var amount: Float,
    @SerializedName("payment_method") var paymentMethod: PaymentMethod
)