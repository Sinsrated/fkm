package io.dala.tester

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

data class CarouselItem(
    val id: Int,
    @DrawableRes val imageResId: Int,
    val contentDescription: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HomeScreen(onLogout: () -> Unit, navController: NavController) {




    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
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
            Column(modifier = Modifier.padding(it)) {
                Column {
                    val items = remember {
                        listOf(
                            CarouselItem(0, R.drawable.photo1, "cupcake"),
                            CarouselItem(1, R.drawable.photo2, "donut"),
                            CarouselItem(2, R.drawable.photo3, "eclair"),
                            CarouselItem(3, R.drawable.photo4, "froyo"),
                            CarouselItem(0, R.drawable.photo5, "cupcake"),
                            CarouselItem(1, R.drawable.photo6, "donut"),
                            CarouselItem(2, R.drawable.photo7, "eclair"),
                            CarouselItem(3, R.drawable.photo8, "froyo"),
                            CarouselItem(0, R.drawable.photo9, "cupcake"),
                            CarouselItem(1, R.drawable.photo10, "donut"),

                            )
                    }

                    HorizontalMultiBrowseCarousel(
                        //userScrollEnabled = false,
                        state = rememberCarouselState { items.count() },
                        preferredItemWidth = 186.dp,
                        itemSpacing = 8.dp,
                    ) { i ->
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
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    HomeContentIconButton(
                        iconResId = R.drawable.profile,
                        text = "Profile",
                        onClick = {
                            navController.navigate("Profile")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.homework,
                        text = "Homework",
                        onClick = {
                            navController.navigate("Homework")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.grade,
                        text = "Grades",
                        onClick = {
                            navController.navigate("Grades")
                        }
                    )
                    HomeContentIconButton(
                        iconResId = R.drawable.news,
                        text = "News",
                        onClick = {
                            navController.navigate("News")
                        }
                    )
                }
                Column {
                    Text(
                        text = "Head Teacher's Message",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    // You can replace this with a string resource
                    val headTeacherMessage =
                        "Welcome to our school! We are committed to providing a nurturing and stimulating environment where every student can achieve their full potential. We believe in fostering a love for learning and encouraging our students to become responsible and compassionate global citizens. We look forward to a productive and exciting academic year ahead!"

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = headTeacherMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    )
}



@Composable
fun HomeContentIconButton(
    iconResId: Int,
    text : String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick, modifier = Modifier.size(64.dp)) {

            Image(
                painter = painterResource(id = iconResId),
                contentDescription = text,
                modifier = Modifier.size(40.dp)
            )
        }
        Text(text = text)
    }
}

