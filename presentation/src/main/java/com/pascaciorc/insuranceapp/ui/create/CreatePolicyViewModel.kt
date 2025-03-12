package com.pascaciorc.insuranceapp.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.domain.usecase.CreatePolicyUseCase
import com.pascaciorc.insuranceapp.entities.PolicyItem
import com.pascaciorc.insuranceapp.mapper.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePolicyViewModel @Inject constructor(
    private val createPolicyUseCase: CreatePolicyUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<CreatePolicyState> =
        MutableStateFlow(CreatePolicyState())
    val uiState = _uiState.asStateFlow()

    fun createPolicy(item: PolicyItem) {
        viewModelScope.launch((Dispatchers.IO)) {
            val success = createPolicyUseCase.invoke(CreatePolicyUseCase.Params(item.toDomain()))
            _uiState.update { it.copy(success = success) }
        }
    }
}