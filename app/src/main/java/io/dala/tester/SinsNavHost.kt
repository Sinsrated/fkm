package io.dala.tester.io.dala.tester

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import io.dala.tester.HomeScreen
import io.dala.tester.LoginScreen
import io.dala.tester.RegisterScreen
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
=======
import io.dala.tester.aboutUsScreen
import io.dala.tester.attendanceScreen
import io.dala.tester.servicesScreen
>>>>>>> Stashed changes
=======
import io.dala.tester.aboutUsScreen
import io.dala.tester.attendanceScreen
import io.dala.tester.servicesScreen
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
        composable("Aboutus") {
            aboutUsScreen(
                navController = navController,
                onHome = {
                    navController.navigate("home")
                    {
                        popUpTo(navController.graph.startDestinationId)
                        {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
            )
        }
        composable("Services") {
            servicesScreen(
                navController = navController,
                onHome = {
                    navController.navigate("home")
                    {
                        popUpTo(navController.graph.startDestinationId)
                        {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable("Attendance") {
            attendanceScreen( navController= navController,
                onHome = {
                    navController.navigate("home")
                    {
                        popUpTo(navController.graph.startDestinationId)
                        {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )


        }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
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