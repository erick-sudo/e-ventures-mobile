package com.eventures.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventures.models.Loan
import com.eventures.repository.EVenturesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoansViewModel: ViewModel() {

    private val eVenturesRepository = EVenturesRepository()

    private var _loansFlow = MutableStateFlow<List<Loan>>(emptyList())

    val loansFlow
        get() = _loansFlow.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                _loansFlow.value = eVenturesRepository.fetchLoans(
                    "",
                    1,
                    10
                )
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }

    suspend fun fetchLoans(pageNumber: Int, pageSize: Int) {
        _loansFlow.value = eVenturesRepository.fetchLoans(
            "",
            pageNumber,
            pageSize
        )
    }
}