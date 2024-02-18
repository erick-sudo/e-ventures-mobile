package com.eventures.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.eventures.extensions.toCurrency
import com.eventures.models.LoanInfo
import java.time.temporal.ChronoUnit

@Composable
fun LoanRepaymentScreen(
    navHostController: NavHostController,
    loanId: String
) {

    val loanInfo by remember {
        mutableStateOf(
            LoanInfo(
                id = loanId,
                principalAmount = 45678f,
                loanDuration = 10f,
                rateOfInterest = 0.1f,
                totalPay = 56789f,
                repaymentInterval = ChronoUnit.WEEKS,
                installment = 6500f,
                daysElapsed = 25,
                elapsedPeriods = 5,
                currentPeriod = 6f,
                repaidAmount = 340000f,
                balance = 22456f,
            )
        )
    }

    val infoFields by remember {
        mutableStateOf(
            listOf(
                "Principal amount" to "${loanInfo.principalAmount}",
                "Loan Duration" to "${loanInfo.loanDuration} ${loanInfo.repaymentInterval}",
                "Rate of interest" to "${loanInfo.rateOfInterest}",
                "Total pay at the end of the loan duration" to "${loanInfo.totalPay}",
                "Repayment interval" to "${loanInfo.repaymentInterval}",
                "Periodic installment" to loanInfo.installment.toDouble().toCurrency(),
                "Total number of days elapsed" to "${loanInfo.daysElapsed}",
                "Completely elapsed periods to date" to "${loanInfo.elapsedPeriods}",
                "Current period" to "${loanInfo.currentPeriod}",
                "Total repaid amount" to loanInfo.repaidAmount.toDouble().toCurrency(),
                "Loan Balance" to loanInfo.balance.toDouble().toCurrency()
            )
        )
    }

    LazyColumn {

        item {
            Icon(
                modifier = Modifier
                    .clickable {
                               UseNavigate.navigate(navHostController, NavRoutes.Home)
                    },
                imageVector = Icons.Filled.ArrowBack, contentDescription = "Move back")
        }

        items(infoFields) {(key, value) ->
            Card(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    fontWeight = FontWeight.Bold,
                    text = key
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 30.dp, vertical = 10.dp),
                    fontFamily = FontFamily.Monospace,
                    text = value
                )
            }
        }
    }
}