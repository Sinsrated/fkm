package io.dala.tester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aboutUsScreen(navController: NavHostController,
                  onHome: () -> Unit): () -> Unit {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AboutUs") })
        },

        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.padding(16.dp)) {
                    IconButton(
                        onClick = { navController.navigate("Home") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(Icons.Filled.Home, contentDescription = "Navigate to Home")
                    }
                    IconButton(
                        onClick = { navController.navigate("Aboutus") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = "Navigate to Aboutus"
                        ) // Example icon
                    }
                    IconButton(
                        onClick = { navController.navigate("Services") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = "Navigate to Services"
                        ) // Example icon
                    }
                    // Add more IconButton instances for other destinations as needed
                }
            }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {




                Image(painter = painterResource(id = R.drawable.test), contentDescription = "test")


            }
        },
    )
    return onHome
}
