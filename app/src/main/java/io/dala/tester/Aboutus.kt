package io.dala.tester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
                  onHome: () -> Unit): () -> Unit
{
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("AboutUs") })
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                Text("About Us")
                Image(painter = painterResource(id = R.drawable.test), contentDescription = "test")

                Button(onClick = onHome, modifier = Modifier.padding(8.dp)) {
                    Text("onHome")

                }
            }
        }
    )
    return onHome
}
