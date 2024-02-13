package com.eventures.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eventures.extensions.toCurrency
import com.eventures.models.InterestType
import com.eventures.models.Loan
import com.eventures.models.LoanStatus
import java.time.temporal.ChronoUnit
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoanItem(
    modifier: Modifier = Modifier,
    loan: Loan
) {

    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = loan.amount.toDouble().toCurrency("KSH"),
                fontWeight = FontWeight.ExtraBold
            )
            Box(
                modifier = Modifier
                    .background(color = loan.status.indicator())
                    .padding(horizontal = 6.dp, vertical = 5.dp)
            ) {
                Text(
                    text = loan.status.name.uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoanItemPreview() {
    val loan = Loan(
        id = UUID.randomUUID().toString(),
        status = LoanStatus.FailedDisbursement,
        amount = 45500f,
        repaymentInterval = ChronoUnit.WEEKS,
        firstRepaymentDate = "2024-02-01",
        interestRate = 0.1f,
        typeOfInterest = InterestType.SIMPLE,
        clientId = UUID.randomUUID().toString(),
        loanOfficerId = UUID.randomUUID().toString(),
        vaultId = UUID.randomUUID().toString()
    )

    LoanItem(
        loan = loan
    )
}