package com.pascaciorc.insuranceapp.entities

data class PolicyItem(
    val id: Int = 0,
    val issueDate: Long,
    val expiryDate: Long,
    val insuredName: String,
    val amount: Float,
    val type: Int,
)