package com.pascaciorc.domain.entities

data class PolicyEntity(
    val id: Int = 0,
    val issueDate: Long,
    val expiryDate: Long,
    val insuredName: String,
    val amount: Float,
    val type: Int,
)
