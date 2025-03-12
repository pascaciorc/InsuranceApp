package com.pascaciorc.domain.repository

import com.pascaciorc.domain.entities.PolicyEntity


interface PoliciesRepository {
    fun getPolicies(): List<PolicyEntity>
    fun setPolicy(entity: PolicyEntity): Boolean
}