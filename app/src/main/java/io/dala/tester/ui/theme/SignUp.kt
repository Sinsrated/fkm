package io.dala.tester.ui.theme

import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.DefaultTab.AlbumsTab.value
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonDefaults.containerColor
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.dala.tester.R


@Composable
fun SignInScreen( onRegisterSuccess: () -> Unit, navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var Email by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var PasswordVisible by remember { mutableStateOf(false) }
    var ConfirmPassword by remember { mutableStateOf("") }
    var ConfirmPasswordVisible by remember { mutableStateOf(false) }
    val passwordsMatch = Password == ConfirmPassword
    val showError = ConfirmPassword.isNotEmpty() && !passwordsMatch

    Box(modifier = Modifier.padding(5.dp)){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Background",
            modifier = Modifier.padding(5.dp),
            contentScale = ContentScale.Crop
        )
    }

    Column() {
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = { Text("username") },
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
                val imageResId = if (PasswordVisible)
                    R.drawable.visibilityoff
                else
                   R.drawable.visibility
                val description = if (PasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { PasswordVisible = !PasswordVisible }) {
                   Image(painter = painterResource(id = imageResId),
                       description)
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
                val imageResId = if (ConfirmPasswordVisible)
                   R.drawable.visibilityoff
                else
                   R.drawable.visibility
                val description = if (ConfirmPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { ConfirmPasswordVisible = !ConfirmPasswordVisible }) {
                    Image(painter = painterResource(id = imageResId),
                        description)
                }
            }
        )
        Button(
            onClick = {
            if (username.isNotEmpty() && Email.isNotEmpty() && Password.isNotEmpty()) {
                if (username == "joel" && Email== "joelkmugerwa@gmail.com" && Password == "money"){
                onRegisterSuccess()
            } else {
                Toast.makeText(context, "Empty username or password", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        }

        },enabled = passwordsMatch && Password.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF_E61D26))
        ){
            Text(text = "Sign In"

            )

        }

    }
}