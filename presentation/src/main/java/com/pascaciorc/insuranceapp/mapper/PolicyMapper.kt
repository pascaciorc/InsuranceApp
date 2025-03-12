package com.pascaciorc.insuranceapp.mapper

import com.pascaciorc.domain.entities.PolicyEntity
import com.pascaciorc.insuranceapp.entities.PolicyItem

fun PolicyEntity.toPresentation() = PolicyItem(
    id = id,
    issueDate = issueDate,
    expiryDate = expiryDate,
    insuredName = insuredName,
    amount = amount,
    type = type,
)