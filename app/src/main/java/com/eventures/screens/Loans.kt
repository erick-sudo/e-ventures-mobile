package com.eventures.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.eventures.R
import com.eventures.ui.EmptyResults
import com.eventures.ui.LoanItem
import com.eventures.ui.UnderlinedNavRail
import com.eventures.viewmodels.LoansViewModel
import com.eventures.viewmodels.LoginViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Loans(
    navHostController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
    loansViewModel: LoansViewModel = viewModel()
) {

    val loginUiState by loginViewModel.loginUiStateFlow.collectAsState()

    val loans by loansViewModel.loansFlow.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        val coroutineScope = rememberCoroutineScope()

        val loanStatusPager = rememberPagerState {
            3
        }

//        LaunchedEffect(Unit) {
//            loansViewModel.fetchLoans(
//                loginUiState.authorizationToken?.accessToken ?: "",
//                1,
//                10
//            )
//        }

        val tabs: List<@Composable (MutableIntState) -> Unit> = listOf("Pending", "Active", "Completed").map { tab -> { state ->
            Text(
                text = tab,
                modifier = Modifier
                    .padding(vertical = 8.dp),
                )
            }
        }

        val loanStatusPages: List<@Composable ()-> Unit> = remember {
            listOf(
                @Composable {
                    EmptyResults {
                        Text(text = "You do not have any loans pending approval")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                },
                @Composable {
                    EmptyResults {
                        Text(text = "You do not have any active loans")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                },
                @Composable {
                    EmptyResults {
                        Text(text = "No loan history found")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                }
            )
        }

        UnderlinedNavRail(
            modifier = Modifier,
            onChange = { coroutineScope.launch { loanStatusPager.animateScrollToPage(it) } },
            items = tabs
        )

        HorizontalPager(state = loanStatusPager) { page ->
            //loanStatusPages[page]()

            LazyColumn {
                items(loans.filter { it.status.ordinal in when(page) {
                    1 -> (5..<6)
                    2 -> (6..<7)
                    else -> 0..4
                } }) { loan ->
                    LoanItem(loan = loan)
                }
            }
        }
    }
}