package com.pascaciorc.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.pascaciorc.data.entities.PolicyData

@Dao
interface InsuranceDao {
    @Query("SELECT * FROM policy")
    fun getPolicies(): List<PolicyData>

    @Insert
    fun setPolicy(data: PolicyData)
}