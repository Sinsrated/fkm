package io.dala.tester.io.dala.tester

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.dala.tester.HomeScreen
import io.dala.tester.LoginScreen
import io.dala.tester.RegisterScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

lateinit var onRegisterSuccess: () -> Unit

@Composable
fun SinsNavHost(navController: Any) {


    val navController = rememberNavController()
    val authViewModel: AuthViewmodel = viewModel()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController, startDestination = "register"
        )
    {
        composable("register") {
            RegisterScreen(navController)
            onRegisterSuccess = {
                navController.navigate("login") {
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        }

        composable("login"){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("home"){
                        popUpTo(navController.graph.startDestinationId){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("home"){
            HomeScreen(
                onLogout ={
                    authViewModel.logout()
                    navController.navigate("login"){
                        popUpTo(navController.graph.startDestinationId){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterSuccess: () -> Unit){

}


class AuthViewmodel(isLoggedIn: StateFlow<Boolean>) : ViewModel(){
private val _isLoggedIn = MutableStateFlow(checkInitialLoginState())
val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

fun loginSuccess(){
    _isLoggedIn.value = true
}

    fun logout() {
        _isLoggedIn.value= false
    }

private fun checkInitialLoginState(): Boolean{


return false
}
}