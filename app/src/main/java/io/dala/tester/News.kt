package io.dala.tester.io.dala.tester

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.dala.tester.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavHostController,
               onHome: () -> Unit): () -> Unit {

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("News Article") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp) // Add padding around the content
                .verticalScroll(rememberScrollState()) // Make the column scrollable
        ) {

            Image(painter = painterResource(id = R.drawable.photo1),
                contentDescription = "news",)

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Breaking News: Jetpack Compose is Awesome!", // Replace with actual title
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 3. Content
            Text(
                text = "More information will be Communicated here"
                    .trimIndent(), // Replace with actual content
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    return onHome
}

