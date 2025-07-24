package io.dala.tester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HomeScreen(onLogout: () -> Unit, navController: NavController) {
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Home") })}
        ,
        bottomBar = {
            BottomAppBar {
                Row(modifier = Modifier.padding( 10.dp)) {
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
            Column ( modifier = Modifier.padding(it))
            {
                Row(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween)
                {
                    HomeContentIconButton(
                        iconResId=R.drawable.profile,
                        text = "Profile",
                        onClick = {navController.navigate("Profile")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.homework,
                        text = "Homework",
                        onClick = {navController.navigate("Homework")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.grade,
                        text = "Grades",
                        onClick = {
                            navController.navigate("Grades")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.news,
                        text = "News",
                        onClick = {
                            navController.navigate("News")
                        }
                    )
                }

            }
        }
    )
}
@Composable
fun HomeContentIconButton(
    iconResId: Int,
    text : String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick, modifier = Modifier.size(64.dp)) {

            Image(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(text = text)
    }
}
