package com.eventures.models

import com.google.gson.annotations.SerializedName
import java.time.temporal.ChronoUnit

data class Loan(
    @SerializedName("id") var id: String = "",
    @SerializedName("status") var status: LoanStatus = LoanStatus.Pending,
    @SerializedName("amount") var amount: Float,
    @SerializedName("repayment_interval") var repaymentInterval: ChronoUnit,
    @SerializedName("repayment_duration") var repaymentDuration: Float = 1f,
    @SerializedName("first_repayment_date") var firstRepaymentDate: String,
    @SerializedName("interest_rate") var interestRate: Float,
    @SerializedName("type_of_interest") var typeOfInterest: InterestType,
    @SerializedName("client_id") var clientId: String,
    @SerializedName("loan_officer_id") var loanOfficerId: String,
    @SerializedName("vault_id") var vaultId: String
)
