package com.pascaciorc.domain.usecase

import com.pascaciorc.domain.entities.PolicyEntity
import com.pascaciorc.domain.repository.PoliciesRepository

class DeletePolicyUseCase(
    private val repository: PoliciesRepository
) {
    class Params(val entity: PolicyEntity)

    fun invoke(params: Params) = repository.deletePolicy(params.entity)
}