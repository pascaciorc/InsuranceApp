package com.pascaciorc.domain.usecase

import com.pascaciorc.domain.entities.PolicyEntity
import com.pascaciorc.domain.repository.PoliciesRepository

class CreatePolicyUseCase(
    private val repository: PoliciesRepository
) {
    class Params(val entity: PolicyEntity)

    fun invoke(params: Params) = repository.setPolicy(params.entity)
}