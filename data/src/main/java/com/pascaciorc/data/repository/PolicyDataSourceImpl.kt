package com.pascaciorc.data.repository

import com.pascaciorc.data.db.InsuranceDao
import com.pascaciorc.data.entities.PolicyData

class PolicyDataSourceImpl(
    private val dao: InsuranceDao
) : PolicyDataSource {
    override fun getPolicies() = dao.getPolicies()
    override fun setPolicy(data: PolicyData): Boolean {
        dao.setPolicy(data)
        return true
    }
}