package com.eventures.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eventures.extensions.toCurrency
import com.eventures.models.InterestType
import com.eventures.models.Loan
import com.eventures.models.LoanRepayment
import com.eventures.models.LoanStatus
import java.time.temporal.ChronoUnit
import java.util.UUID

@Composable
fun LoanItem(
    modifier: Modifier = Modifier,
    loan: Loan
) {

    Card(
        modifier = Modifier
            .clickable {  },
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {

            Text(
                text = loan.amount.toDouble().toCurrency("KSH"),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "${loan.interestRate * 100}%")
                Box(
                    modifier = Modifier
                        .background(color = loan.status.indicator())
                        .padding(horizontal = 16.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = loan.status.name.uppercase(),
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = "${loan.repaymentDuration} ${loan.repaymentInterval}",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Row {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(
                        fontWeight = FontWeight.Bold
                    )) {
                        append(loan.firstRepaymentDate)
                    }
                    withStyle(SpanStyle()) {
                        append(" -> ")
                    }
                    withStyle(SpanStyle(
                        fontWeight = FontWeight.Bold
                    )) {
                        append(loan.firstRepaymentDate)
                    }
                })
            }
        }
    }
}

@Composable
fun RepaymentItem(
    loanRepayment: LoanRepayment
) {
    Column(
        modifier = Modifier
            .padding(top = 15.dp, start = 10.dp, end = 10.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 5.dp),
            text = loanRepayment.collectionDate
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp, horizontal = 5.dp),
                text = loanRepayment.amount.toDouble().toCurrency("KSH")
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp, horizontal = 5.dp),
                text = loanRepayment.paymentMethod.toString()
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = MaterialTheme.colorScheme.secondary)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoanItemPreview() {
    val loan = Loan(
        id = UUID.randomUUID().toString(),
        status = LoanStatus.Approved,
        amount = 45578900f,
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