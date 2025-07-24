package io.dala.tester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import io.dala.tester.io.dala.tester.SinsNavHost
import io.dala.tester.ui.theme.TesterTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesterTheme {
                Scaffold{
                    val navController = rememberNavController()

                    Surface(modifier = Modifier.padding(it)) {

                        SinsNavHost()
                    }
                }
            }
        }
    }
}
