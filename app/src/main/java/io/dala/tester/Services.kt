package io.dala.tester

import android.R.attr.icon
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun servicesScreen(navController: NavHostController,
                   onHome: () -> Unit): () -> Unit
{



    Scaffold (
        topBar = {
            TopAppBar(title = { Text("Services") })
        },
        bottomBar = {
            BottomAppBar{
                Row(modifier = Modifier.padding(10.dp)) {
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

             innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(16.dp) // Add padding around the content
                        .verticalScroll(rememberScrollState()) // Make content scrollable
                ) {
                    ServiceCard(

                        iconDrawableResId = R.drawable.school,
                        title = "Comprehensive Education",
                        description = "We offer the Ugandan National Curriculum from Nursery to Primary 7, fostering academic excellence and holistic development. Our programs include a wide range of extracurricular activities to nurture diverse talents."
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.bus,
                        title = "Safe School Transportation",
                        description = "Reliable and secure transport services are available for our students. We cover various routes to ensure your child's convenient commute to and from school. Please inquire at the office for specific route details and registration."
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.daycare,
                        title = "Day Care Services",
                        description = "Peace of mind for working parents. Our reliable day care provides a nurturing and educational environment for young children, supervised by experienced caregivers. We ensure your child's well-being while you focus on your day.")

                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.licensing, // Or a custom UNEB logo if you have one
                        title = "Accredited UNEB Center",
                        description = "Our school is an officially recognized UNEB (Uganda National Examinations Board) examination center. This provides a familiar and supportive environment for our candidates during national assessments like PLE."

                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.lunch,
                        title = "Nutritious School Meals",
                        description = "We provide well-balanced and nutritious meals (breakfast and lunch) prepared hygienically within the school premises, ensuring students are energized for learning. (Optional: Add details about dietary options if any)."
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.library,
                        title = "Library",
                        description = "Our well-stocked library provides a quiet space for reading, research, and learning. We offer a wide range of books, periodicals, and digital resources to support students' academic journey."
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.sports,
                        title = "Sports",
                        description = "We encourage physical fitness and teamwork through a variety of sports activities. Students have opportunities to participate in [mention 1-2 popular sports like P.E., football, netball] and develop their athletic skills in a supportive environment."
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    ServiceCard(
                        iconDrawableResId = R.drawable.computer,
                        title = "Ict",
                       description = "We provide students with essential ICT skills for the digital age. Our computer lab is equipped with [mention number or type of computers, e.g., modern computers] and internet access, where students learn [mention 1-2 key things, e.g., basic computer operations, productivity software, safe internet usage]."
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

        }
    )
    return onHome
}
@Composable
fun ServiceCard(
    iconDrawableResId: Int? = null,
    title: String,
    description: String,
    iconVector: ImageVector? = null,
    imageRes: Int? = null
) {
    Card(
        //colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (iconVector != null) {
                    Icon(
                        imageVector = iconVector,
                        contentDescription = "$title icon",
                        modifier = Modifier.size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                } else if (iconDrawableResId != null) {
                    Icon(
                        painter = painterResource(id = iconDrawableResId),
                        contentDescription = "$title icon",
                        modifier = Modifier.size(40.dp),

                    )
                }
                if (iconVector != null || iconDrawableResId != null) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            if (imageRes != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "$title logo",
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

