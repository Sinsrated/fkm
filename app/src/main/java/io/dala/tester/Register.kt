package io.dala.tester


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.dala.tester.ui.theme.SignInScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    var showLogin by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(if (showLogin) "Login" else "Register Here") })
        },
        content = {
            Column(
                modifier = Modifier.padding(it)
            ) {

                if (showLogin) {
                    LoginScreen(onLoginSuccess = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }, navController = navController)
                    Spacer(modifier = Modifier.padding(16.dp))
                    TextButton(onClick = { showLogin = false }) {
                        Text("Don't have an account? Register here")
                    }
                } else {
                    SignInScreen(onRegisterSuccess = {
                        navController.navigate("home") {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }, navController = navController)
                    Spacer(modifier = Modifier.padding(16.dp))
                    TextButton(onClick = { showLogin = true }) {
                        Text("Already have an account? Login here")
                    }
                }
            }
        }
    )
}
