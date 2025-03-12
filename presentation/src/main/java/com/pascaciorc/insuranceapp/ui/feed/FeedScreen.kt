package com.pascaciorc.insuranceapp.ui.feed

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pascaciorc.insuranceapp.R
import com.pascaciorc.insuranceapp.entities.PolicyItem
import com.pascaciorc.insuranceapp.ui.helper.toDateFormat
import com.pascaciorc.insuranceapp.ui.helper.toDrawable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(
    modifier: Modifier,
    onAddClicked: () -> Unit,
    onCardClicked: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    viewModel.getPolicies()
    val state by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Insurance App") },
                actions = {
                    IconButton(onClick = { onAddClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        content = { padding ->
            Column(modifier = modifier.padding(padding)) {
                LazyColumn {
                    items(items = state.policies) { item ->
                        PolicyCard(item) { onCardClicked.invoke(Json.encodeToString(item)) }
                    }
                }
            }
        }
    )
}

@Composable
fun PolicyCard(item: PolicyItem, onCardClicked: () -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        onClick = { onCardClicked.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Policy ID: ${item.id}")
                Text(text = "Owner: ${item.insuredName}")
                Text("Amount: $${item.amount}")
                Text(text = "Expiry Date: ${item.expiryDate.toDateFormat()}")
            }
            Column {
                Image(
                    painter = painterResource(item.type.toDrawable()), "Insurance type",
                    modifier = Modifier.size(80.dp),
                )
            }
        }
    }
}