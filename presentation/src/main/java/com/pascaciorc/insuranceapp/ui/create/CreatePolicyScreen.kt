package com.pascaciorc.insuranceapp.ui.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pascaciorc.insuranceapp.R
import com.pascaciorc.insuranceapp.entities.PolicyItem
import com.pascaciorc.insuranceapp.ui.feed.FeedViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePolicyScreen(
    modifier: Modifier,
    onBackClicked: () -> Unit,
    viewModel: CreatePolicyViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }
    val policyTypes = listOf("Life", "Travel", "Pet", "Car")
    var ownerText by rememberSaveable { mutableStateOf("") }

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
                Spacer(modifier = Modifier.size(24.dp))
                Text(
                    "Create new insurance policy",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.size(20.dp))
                TextField(
                    value = ownerText,
                    onValueChange = { ownerText = it },
                    label = { Text("Owner name") }
                )
                Spacer(modifier = Modifier.size(20.dp))
                DropDownPolicyMenu(
                    isDropDownExpanded,
                    itemPosition,
                    policyTypes,
                )
                Spacer(modifier = Modifier.size(24.dp))
                Button(onClick = {
                    val nowMillis = Calendar.getInstance().timeInMillis
                    val oneMonth = Calendar.getInstance()
                    oneMonth.add(Calendar.MONTH, 1)
                    val oneMonthMillis = oneMonth.timeInMillis
                    viewModel.createPolicy(
                        PolicyItem(
                            issueDate = nowMillis,
                            expiryDate = oneMonthMillis,
                            insuredName = ownerText,
                            type = itemPosition.intValue,
                            amount = when (itemPosition.intValue) {
                                0 -> 3000000F
                                1 -> 150000F
                                2 -> 100000F
                                3 -> 250000F
                                else -> 0F
                            }
                        )
                    )
                }) {
                    Text("Create")
                }
            }
        }
    )
    if (state.success) {
        onBackClicked.invoke()
    }
}

@Composable
fun DropDownPolicyMenu(
    isDropDownExpanded: MutableState<Boolean>,
    itemPosition: MutableIntState,
    policyTypes: List<String>
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            isDropDownExpanded.value = true
        }
    ) {
        Text(text = "Type: ")
        Text(text = policyTypes[itemPosition.intValue])
        Image(
            painter = painterResource(id = R.drawable.drop_down),
            contentDescription = "DropDown Icon"
        )
    }
    DropdownMenu(
        expanded = isDropDownExpanded.value,
        onDismissRequest = {
            isDropDownExpanded.value = false
        }) {
        policyTypes.forEachIndexed { index, policy ->
            DropdownMenuItem(
                text = {
                    Text(text = policy)
                },
                onClick = {
                    isDropDownExpanded.value = false
                    itemPosition.intValue = index
                })
        }
    }
}

