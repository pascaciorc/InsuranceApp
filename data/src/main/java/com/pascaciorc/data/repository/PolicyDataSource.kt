package com.pascaciorc.data.repository

import com.pascaciorc.data.entities.PolicyData

interface PolicyDataSource {
    fun getPolicies(): List<PolicyData>
}