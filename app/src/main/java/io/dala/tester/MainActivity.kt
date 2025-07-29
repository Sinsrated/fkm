package io.dala.tester

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import io.dala.tester.io.dala.tester.SinsNavHost
import io.dala.tester.ui.theme.TesterTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TesterTheme {
                Box (modifier = Modifier.padding(10.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Background Image",
                        modifier = Modifier.padding(10.dp),
                        contentScale = ContentScale.Crop
                    )
                }
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
