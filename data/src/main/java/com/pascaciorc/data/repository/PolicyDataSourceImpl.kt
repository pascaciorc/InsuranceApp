package com.pascaciorc.data.repository

import com.pascaciorc.data.db.InsuranceDao

class PolicyDataSourceImpl(
    private val dao: InsuranceDao
) : PolicyDataSource {
    override fun getPolicies() = dao.getPolicies()
}