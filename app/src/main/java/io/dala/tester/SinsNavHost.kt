package io.dala.tester.io.dala.tester

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.dala.tester.HomeScreen
import io.dala.tester.RegisterScreen
import io.dala.tester.aboutUsScreen
import io.dala.tester.attendanceScreen
import io.dala.tester.servicesScreen

lateinit var onRegisterSuccess: () -> Unit

@Composable
fun SinsNavHost() {


    val navController = rememberNavController()
 //   val authViewModel: AuthViewmodel = viewModel()
 //   val isLoggedIn by authViewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = navController, startDestination = "register"
        ) {
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
                },
                onRegisterSuccess = {
                    navController.navigate("register")
                }
            )
        }
        composable("home"){
            HomeScreen(
                onLogout ={
                   // authViewModel.logout()
                    navController.navigate("login"){
                        popUpTo(navController.graph.startDestinationId){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                navController = navController
            )
        }
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
//                onHome = {
//                    navController.navigate("home")
//                    {
//                        popUpTo(navController.graph.startDestinationId)
//                        {
//                            inclusive = true
//                        }
//                        launchSingleTop = true
//                    }
//                }
            )
        }
    }
}

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, onRegisterSuccess: () -> Unit){

}
