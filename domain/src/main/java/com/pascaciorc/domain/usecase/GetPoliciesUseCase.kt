package com.pascaciorc.domain.usecase

import com.pascaciorc.domain.repository.PoliciesRepository

class GetPoliciesUseCase(
    private val repository: PoliciesRepository
) {
    fun invoke() = repository.getPolicies()
}