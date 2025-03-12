package com.pascaciorc.insuranceapp.ui.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pascaciorc.insuranceapp.entities.PolicyItem
import com.pascaciorc.insuranceapp.ui.helper.toDateFormat
import com.pascaciorc.insuranceapp.ui.helper.toDrawable
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPolicyScreen(
    modifier: Modifier,
    item: String,
    onBackClicked: () -> Unit,
    viewModel: EditPolicyViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Insurance App") },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val policyItem = Json.decodeFromString<PolicyItem>(item)
                Spacer(modifier = Modifier.size(24.dp))
                Image(
                    painter = painterResource(policyItem.type.toDrawable()), "Insurance type",
                    modifier = Modifier.size(120.dp),
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Policy ID: ${policyItem.id}")
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Owner: ${policyItem.insuredName}")
                Spacer(modifier = Modifier.size(20.dp))
                Text("Amount: $${policyItem.amount}")
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Date of Issue: ${policyItem.issueDate.toDateFormat()}")
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Expiry Date: ${policyItem.expiryDate.toDateFormat()}")
                Spacer(modifier = Modifier.size(20.dp))
                Button(onClick = {
                    viewModel.deletePolicy(policyItem)
                }) {
                    Text("Delete policy")
                }
            }
        }
    )
    if (state.success) {
        onBackClicked.invoke()
    }
}



