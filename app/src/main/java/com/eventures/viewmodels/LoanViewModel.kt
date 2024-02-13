package com.eventures.viewmodels

import androidx.lifecycle.ViewModel
import com.eventures.models.Loan
import com.eventures.repository.EVenturesRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class LoanViewModel: ViewModel() {

    private val eVenturesRepository = EVenturesRepository()

    private var _loanFlow = MutableSharedFlow<Loan>()

    val loanFlow
        get() = _loanFlow.asSharedFlow()

    suspend fun fetchLoan(loanId: String) {
        _loanFlow.emit(
            eVenturesRepository
                .fetchLoan(
                    authorization = "",
                    loanId = loanId
                )
        )
    }
}