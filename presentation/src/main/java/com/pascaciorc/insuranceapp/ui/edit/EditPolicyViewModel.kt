package com.pascaciorc.insuranceapp.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pascaciorc.domain.usecase.DeletePolicyUseCase
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
class EditPolicyViewModel @Inject constructor(
    private val deletePolicyUseCase: DeletePolicyUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<EditPolicyState> =
        MutableStateFlow(EditPolicyState())
    val uiState = _uiState.asStateFlow()

    fun deletePolicy(item: PolicyItem) {
        viewModelScope.launch((Dispatchers.IO)) {
            val success = deletePolicyUseCase.invoke(DeletePolicyUseCase.Params(item.toDomain()))
            _uiState.update { it.copy(success = success) }
        }
    }
}