package com.pascaciorc.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "policy")
data class PolicyData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val issueDate: Long,
    val expiryDate: Long,
    val insuredName: String,
    val amount: Float,
    val type: Int,
)
