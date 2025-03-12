package com.pascaciorc.insuranceapp.ui.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.domain.usecase.GetPoliciesUseCase
import com.pascaciorc.insuranceapp.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getPoliciesUseCase: GetPoliciesUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<FeedState> = MutableStateFlow(FeedState(emptyList()))
    val uiState = _uiState.asStateFlow()

    fun getPolicies() {
        viewModelScope.launch((Dispatchers.IO)) {
            val policies = getPoliciesUseCase.invoke().map { it.toPresentation() }
            _uiState.update { it.copy(policies = policies) }
        }
    }
}
