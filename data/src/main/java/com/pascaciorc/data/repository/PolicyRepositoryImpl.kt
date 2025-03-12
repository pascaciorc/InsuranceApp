package com.pascaciorc.data.repository

import com.pascaciorc.data.entities.toDomain
import com.pascaciorc.domain.repository.PoliciesRepository

class PolicyRepositoryImpl(
    private val source: PolicyDataSource
) : PoliciesRepository {
    override fun getPolicies() = source.getPolicies().map { it.toDomain() }
}