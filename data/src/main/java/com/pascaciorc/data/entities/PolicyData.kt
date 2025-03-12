package com.pascaciorc.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pascaciorc.domain.entities.PolicyEntity

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

fun PolicyData.toDomain() = PolicyEntity(
    id = id,
    issueDate = issueDate,
    expiryDate = expiryDate,
    insuredName = insuredName,
    amount = amount,
    type = type,
)

fun PolicyEntity.toData() = PolicyData(
    id = id,
    issueDate = issueDate,
    expiryDate = expiryDate,
    insuredName = insuredName,
    amount = amount,
    type = type,
)
