package io.dala.tester

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, navController: NavController) {
    var usernameoremail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column {
        OutlinedTextField(
            value = usernameoremail,
            onValueChange = {usernameoremail = it},
            label = { Text("username or email") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("password") },
            modifier = Modifier.fillMaxWidth(),shape = RoundedCornerShape(15.dp),

            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Done
                else
                    Icons.Filled.Lock
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )

        }
        Button(
            onClick = {
                navController.navigate("home")
            }
        ) {
            Text(text = "Login")
        }


    }
