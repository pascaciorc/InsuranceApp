package com.pascaciorc.data.repository

import com.pascaciorc.data.entities.toData
import com.pascaciorc.data.entities.toDomain
import com.pascaciorc.domain.entities.PolicyEntity
import com.pascaciorc.domain.repository.PoliciesRepository

class PolicyRepositoryImpl(
    private val source: PolicyDataSource
) : PoliciesRepository {
    override fun getPolicies() = source.getPolicies().map { it.toDomain() }

    override fun setPolicy(entity: PolicyEntity): Boolean {
        source.setPolicy(entity.toData())
        return true
    }

    override fun deletePolicy(entity: PolicyEntity): Boolean {
        source.deletePolicy(entity.toData())
        return true
    }
}