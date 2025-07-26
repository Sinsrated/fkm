package io.dala.tester

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun aboutUsScreen(navController: NavHostController,
                  onHome: () -> Unit): () -> Unit {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AboutUs") })
        },

        bottomBar = {
            BottomAppBar {
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
            Column(modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                    Image(
                        painter = painterResource(id = R.drawable.photo10),
                        contentDescription = "Logo",
                        modifier = Modifier.size(200.dp),
                        contentScale = ContentScale.Crop
                    )
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.about_us_title),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF_E61D26),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.about_us_description),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF_EDF107),
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable {
                                val videoId = "49cdAaaVvbg" // Example: Rick Astley
                                val intent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("vnd.youtube:$videoId")
                                )
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://youtu.be/49cdAaaVvbg")
                                )
                                try {
                                    context.startActivity(intent)
                                } catch (e: Exception) {
                                    // YouTube app not found, open in browser
                                    context.startActivity(webIntent)
                                }
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Play Video",
                                modifier = Modifier.size(40.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Watch Our School Tour!", // Or any title
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Click to play video",
                                    style = MaterialTheme.typography.bodySmall
                                )

                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Column {
                        Text(
                            text = "Our Best in PLE 2024",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        val items = remember {
                            listOf(
                                CarouselItem(0, R.drawable.ple1, "cupcake"),
                                CarouselItem(1, R.drawable.ple2, "donut"),
                                CarouselItem(2, R.drawable.ple3, "eclair"),
                                CarouselItem(3, R.drawable.ple4, "froyo"),
                                CarouselItem(0, R.drawable.ple5, "cupcake"),

                                )
                        }

                        HorizontalMultiBrowseCarousel(
                            //userScrollEnabled = false,
                            state = rememberCarouselState { items.count() },
                            preferredItemWidth = 186.dp,
                            itemSpacing = 8.dp,
                        ){i ->
                            val item = items[i]
                            Image(
                                modifier = Modifier
                                    .height(205.dp)
                                    .maskClip(MaterialTheme.shapes.extraLarge),
                                painter = painterResource(id = item.imageResId),
                                contentDescription = item.contentDescription,
                                contentScale = ContentScale.Crop
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Our Location",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val geoUri = "geo: 0.28162,32.55073?q= 0.28162,32.55073(My+School+Name)"
                                // Option 2: Using a search query (more flexible if lat/lon is unknown)
                                // val searchQuery = Uri.encode("Your School Name, City, Country")
                                // val geoUri = "geo:0,0?q=$searchQuery"

                                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
                                mapIntent.setPackage("com.google.android.apps.maps") // Attempt to open in Google Maps app specifically

                                if (mapIntent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(mapIntent)
                                } else {
                                    // Fallback to opening in browser if Google Maps app is not installed
                                    val webMapIntent = Intent(
                                        Intent.ACTION_VIEW,
                                        "https://www.google.com/maps/search/?api=1&query=${
                                            Uri.encode(
                                                "Your School Name, Your City"
                                            )
                                        }".toUri()
                                        // Or use: Uri.parse("https://maps.google.com/?q=<lat>,<lng>")
                                    )
                                    context.startActivity(webMapIntent)
                                }
                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.LocationOn, // Location Icon
                                contentDescription = "View on Map",
                                modifier = Modifier.size(40.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = "Find Us on Google Maps",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Click to open location",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))


                }
            }
        },
    )
    return onHome
}
