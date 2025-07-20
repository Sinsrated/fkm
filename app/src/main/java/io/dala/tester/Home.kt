package io.dala.tester

import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HomeScreen(
    navController: NavHostController,
    onLogout: () -> Unit) {
    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Home") })}
        , content = {
            Column ( modifier = Modifier.padding(it))
            {
                Row (){

                    Button(onClick = {navController.navigate("Aboutus"){

                    }
                    })
                 { Text("AboutUs") }
                }
                Image(painter = painterResource(id = R.drawable.pussy), contentDescription= "pussy"
                , modifier = Modifier.padding(16.dp)
                )
            }
            Button(onClick = onLogout, modifier = Modifier.padding(top = 8.dp)) {
                Text("logout")
            }



        }
    )
}