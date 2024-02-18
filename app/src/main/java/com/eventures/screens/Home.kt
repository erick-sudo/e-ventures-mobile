package com.eventures.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.eventures.R
import com.eventures.extensions.toCurrency
import com.eventures.models.InterestType
import com.eventures.models.Loan
import com.eventures.models.LoanRepayment
import com.eventures.models.LoanStatus
import com.eventures.models.PaymentMethod
import com.eventures.ui.AnimatedCreditScoreIndicator
import com.eventures.ui.RepaymentItem
import com.eventures.viewmodels.LoginViewModel
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.UUID

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    loginViewModel: LoginViewModel
) {

    var repaymentsMoreModalVisible by remember {
        mutableStateOf(false)
    }

    var accountMoreModalVisible by remember {
        mutableStateOf(false)
    }

    val loginUiState by loginViewModel.loginUiStateFlow.collectAsState()

    val currentLoan by remember {
        mutableStateOf(
            Loan(
                id = UUID.randomUUID().toString(),
                status = LoanStatus.Approved,
                amount = 45580f,
                repaymentInterval = ChronoUnit.WEEKS,
                firstRepaymentDate = "2024-02-01",
                interestRate = 0.1f,
                typeOfInterest = InterestType.SIMPLE,
                clientId = UUID.randomUUID().toString(),
                loanOfficerId = UUID.randomUUID().toString(),
                vaultId = UUID.randomUUID().toString()
            )
        )
    }

    LazyColumn {

        item {
            Card(
                shape = RectangleShape,
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(200.dp),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Avatar"
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                    ) {
                        Icon(
                            modifier = Modifier
                                .clickable { accountMoreModalVisible = true },
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "More"
                        )

                        DropdownMenu(
                            expanded = accountMoreModalVisible,
                            onDismissRequest = { accountMoreModalVisible = false }
                        ) {
                            DropdownMenuItem(
                                leadingIcon = { Icon(imageVector = ImageVector.vectorResource(R.drawable.baseline_logout_24), contentDescription = "Log out") },
                                text = { Text(text = "Log Out") },
                                onClick = {
                                    accountMoreModalVisible = false
                                    loginViewModel.changeLoginStatus(0)
                                }
                            )
                        }
                    }
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    text = loginUiState.authorizable?.name ?: "",
                    fontSize = 30.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item {
            Card(
                shape = RectangleShape,
                modifier = Modifier
                    .height(150.dp)
                    .padding(5.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(20.dp),
                    text = "Credit Score"
                )
                AnimatedCreditScoreIndicator(
                    modifier = Modifier
                        .padding(10.dp),
                    creditScore = 7.6f
                )
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ),
                        shape = RectangleShape,
                    ) {
                        Text(text = "Approved", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                        Text(text = "12", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth())
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ),
                        shape = RectangleShape,
                    ) {
                        Text(text = "Disbursed", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                        Text(text = "11", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth())
                    }
                }
                Row {
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ),
                        shape = RectangleShape,
                    ) {
                        Text(text = "Approved", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                        Text(text = "12", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth())
                    }
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(2.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        ),
                        shape = RectangleShape,
                    ) {
                        Text(text = "Disbursed", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth())
                        Text(text = "11", textAlign = TextAlign.Center, modifier = Modifier
                            .padding(bottom = 10.dp)
                            .fillMaxWidth())
                    }
                }
            }
        }
        
        item {
            Column {

                val infos = mapOf(
                    R.drawable.baseline_change_circle_24 to ("Interest Rate" to "${currentLoan.interestRate * 100}"),
                    R.drawable.baseline_lock_clock_24 to ("Loan Duration" to "${currentLoan.repaymentDuration} ${currentLoan.repaymentInterval}"),
                    R.drawable.baseline_start_24 to ("Opening Date" to "${currentLoan.firstRepaymentDate}"),
                    R.drawable.baseline_first_page_24 to ("First Repayment Date" to "${currentLoan.firstRepaymentDate}"),
                    R.drawable.baseline_close_fullscreen_24 to ("Closing Date" to "${currentLoan.firstRepaymentDate}")
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    text = "Current/Last Loan",
                    fontSize = 20.sp
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    text = currentLoan.amount.toDouble().toCurrency("KSH"),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.secondary
                )

                infos.forEach { (icon, info) ->
                    val (key, value) = info
                    Card(
                        modifier = Modifier
                            .padding(2.dp)
                            .fillMaxWidth(),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 5.dp
                        )
                    ) {
                        Row(modifier = Modifier
                            .padding(horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(modifier = Modifier.size(16.dp),imageVector = ImageVector.vectorResource(icon), contentDescription = key)
                            Text(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp, vertical = 5.dp),
                                fontWeight = FontWeight.Bold,
                                text = key
                            )
                        }
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 30.dp, vertical = 10.dp),
                            fontFamily = FontFamily.Monospace,
                            text = value
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 5.dp),
                            text = "Repayments",
                            fontSize = 20.sp
                        )

                        Column {
                            Icon(
                                modifier = Modifier
                                    .clickable { repaymentsMoreModalVisible = true },
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "More"
                            )

                            DropdownMenu(
                                expanded = repaymentsMoreModalVisible,
                                onDismissRequest = { repaymentsMoreModalVisible = false }
                            ) {
                                DropdownMenuItem(text = { Text("Make Repayment") }, onClick = {
                                    repaymentsMoreModalVisible = false
                                })
                            }
                        }
                    }

                    (1..8).forEach { _ ->
                        RepaymentItem(
                            loanRepayment = LoanRepayment(
                                loanId = "",
                                collectionDate = Date().toString(),
                                amount = (7000..10000).random().toFloat(),
                                paymentMethod = PaymentMethod.entries.random()
                            )
                        )
                    }
                }

                Button(
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    onClick = { UseNavigate.navigate(navHostController, "${NavRoutes.Loans.route}/makePayment/loanId") },
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Make payment")
                }
            }
        }
    }
}
