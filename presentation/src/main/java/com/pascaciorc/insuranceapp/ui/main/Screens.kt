package com.pascaciorc.insuranceapp.ui.main

import com.pascaciorc.insuranceapp.entities.PolicyItem
import kotlinx.serialization.Serializable

@Serializable
object FeedScreen

@Serializable
object CreatePolicyScreen

@Serializable
data class EditPolicy(val item: String)