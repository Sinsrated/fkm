package io.dala.tester.ui.theme

import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
fun SignInScreen(navController: NavController) {
    var Sirname by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var PasswordVisible by remember { mutableStateOf(false) }
    var ConfirmPassword by remember { mutableStateOf("") }
    var ConfirmPasswordVisible by remember { mutableStateOf(false) }
    val passwordsMatch = Password == ConfirmPassword
    val showError = ConfirmPassword.isNotEmpty() && !passwordsMatch

    Column() {
        OutlinedTextField(
            value = Sirname,
            onValueChange = {Sirname = it},
            label = { Text("SirName") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)
        )
        OutlinedTextField(
            value =Email,
            onValueChange = {Email = it},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(15.dp)
        )
        OutlinedTextField(
            value = Password,
            onValueChange = {Password = it},
            label = { Text("Password") },
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
        Button(onClick = {
            navController.navigate("home")
        },enabled = passwordsMatch && Password.isNotEmpty()){
            Text(text = "Sign In")

        }

    }
}