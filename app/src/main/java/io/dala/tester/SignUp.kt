package io.dala.tester

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// Dummy user data - In a real app, this should be stored securely
const val CORRECT_USERNAME = "joel"
const val CORRECT_EMAIL = "joelkmugerwa@gmail.com"
const val CORRECT_PASSWORD = "money"

@Composable
fun SignInScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var PasswordVisible by remember { mutableStateOf(false) }
    var ConfirmPassword by remember { mutableStateOf("") }
    var ConfirmPasswordVisible by remember { mutableStateOf(false) }
    var signUpError by remember { mutableStateOf<String?>(null) }
    var signUpSuccessMessage by remember { mutableStateOf<String?>(null) }
    val passwordsMatch = password == ConfirmPassword
    val showError = ConfirmPassword.isNotEmpty() && !passwordsMatch

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("userName") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value =email,
            onValueChange = {email = it},
            label = { Text("email") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("password") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp),
            singleLine = true,
            visualTransformation = if (PasswordVisible)
                VisualTransformation.None else   PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (PasswordVisible)
                    Icons.Filled.Done
                else
                    Icons.Filled.Lock
                val description = if (PasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { PasswordVisible = !PasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            }

        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = ConfirmPassword,
            onValueChange = {ConfirmPassword = it},
            label = { Text("ConfirmPassword") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp),
            isError = true,
            supportingText = {
                if (showError) {
                    Text("Passwords do not match")}},
            singleLine = true,
            visualTransformation = if (ConfirmPasswordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image = if (ConfirmPasswordVisible)
                    Icons.Filled.Done
                else
                    Icons.Filled.Lock
                val description = if (ConfirmPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { ConfirmPasswordVisible = !ConfirmPasswordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (signUpError != null) {
            Text(signUpError!!, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (signUpSuccessMessage != null) {
            Text(signUpSuccessMessage!!, color = MaterialTheme.colorScheme.primary) // Or a success color
            Spacer(modifier = Modifier.height(8.dp))
        }


        Button(onClick = {
            signUpError = null // Reset error
            signUpSuccessMessage = null // Reset success message
            if (username.isBlank() || email.isBlank() || password.isBlank()) {
                signUpError = "Please fill in all fields"
                return@Button
            }
            if (password != ConfirmPassword) {
                signUpError = "Passwords do not match"
                return@Button

            }

            if (!(username != CORRECT_USERNAME && email != CORRECT_EMAIL && password != CORRECT_PASSWORD)) {
                signUpSuccessMessage = "Sign up successful for $username! you can now log in."
                navController.navigate("Home")
                return@Button
            }else {
                signUpError = "Invalid username or password"
                return@Button
            }

        },enabled = passwordsMatch && password.isNotEmpty()){
            Text(text = "Sign In")

        }

    }
}