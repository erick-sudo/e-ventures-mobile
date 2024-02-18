package com.eventures.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventures.models.InterestType
import com.eventures.models.Loan
import com.eventures.models.LoanStatus
import com.eventures.repository.ApiCallWrapper
import com.eventures.repository.EVenturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.temporal.ChronoUnit
import java.util.UUID

class LoansViewModel: ViewModel() {

    private val eVenturesRepository = EVenturesRepository()

    private var _loansFlow = MutableStateFlow<List<Loan>>(emptyList())

    val loansFlow
        get() = _loansFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _loansFlow.value = (1..50).map { Loan(
                id = UUID.randomUUID().toString(),
                status = LoanStatus.entries.toTypedArray().random(),
                amount = 45578900f,
                repaymentInterval = ChronoUnit.WEEKS,
                firstRepaymentDate = "2024-02-01",
                interestRate = 0.1f,
                typeOfInterest = InterestType.SIMPLE,
                clientId = UUID.randomUUID().toString(),
                loanOfficerId = UUID.randomUUID().toString(),
                vaultId = UUID.randomUUID().toString()
            ) }
        }
    }

    suspend fun fetchLoans(authorization: String, pageNumber: Int, pageSize: Int) {
        println("TOKEN '$authorization'")
        ApiCallWrapper.call(
            apiCall = { eVenturesRepository.fetchLoans(
                "Bearer $authorization",
                pageNumber,
                pageSize
            ) },
            onError = { c1, c2, body, msg ->
                println(c1)
                println(c2)
                println(body)
                println(msg)
            }
        )?.also {
            _loansFlow.value = it
        }
    }
}