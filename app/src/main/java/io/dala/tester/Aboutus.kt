package io.dala.tester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Row(modifier = Modifier.padding(10.dp)) {
                    IconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "Home") {
                                navController.navigate("Home")
                            }
                        },
                        enabled = navController.currentDestination?.route != "Home",
                        modifier = Modifier.weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home),
                            contentDescription = "Navigate to Home",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "Aboutus") {
                                navController.navigate("Aboutus")
                            }
                        },
                        enabled = navController.currentDestination?.route != "Aboutus",
                        modifier = Modifier.weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.aboutus),
                            contentDescription = "Navigate to About Us",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            if (navController.currentDestination?.route != "Services") {
                                navController.navigate("Services")
                            }
                        },
                        enabled = navController.currentDestination?.route != "Services",
                        modifier = Modifier.weight(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.services),
                            contentDescription = "Navigate to Services",
                            modifier = Modifier.size(40.dp)
                        )

                    }
                }
            }
        },
        content = {
            Column(modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = "Logo",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.about_us_title),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF_E61D26),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.about_us_description),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF_EDF107),
                    )
                }
            }
        },
    )
    return onHome
}
