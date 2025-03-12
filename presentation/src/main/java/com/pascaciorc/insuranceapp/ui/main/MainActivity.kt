package com.pascaciorc.insuranceapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pascaciorc.insuranceapp.ui.create.CreatePolicyScreen
import com.pascaciorc.insuranceapp.ui.feed.FeedScreen
import com.pascaciorc.insuranceapp.ui.theme.InsuranceAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InsuranceAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = FeedScreen
                ) {
                    composable<FeedScreen> {
                        FeedScreen(
                            modifier = Modifier.padding(),
                            onAddClicked = {
                                navController.navigate(CreatePolicyScreen)
                            }
                        )
                    }
                    composable<CreatePolicyScreen> {
                        CreatePolicyScreen(
                            modifier = Modifier.padding(),
                            onBackClicked = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object FeedScreen

@Serializable
object CreatePolicyScreen
