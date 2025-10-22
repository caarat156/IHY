package com.example.trialvp.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginView {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var showDialog by rememberSaveable { mutableStateOf(false) }

    val isNameValid = isValidName(name)
    val isEmailValid = isValidEmail(email)
    val isPasswordValid = isValidPassword(password)
    val formValid = isNameValid && isEmailValid && isPasswordValid

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedTextField(
            value = name,
            onValueChange = {name=it},
            modifier = Modifier.fillMaxWidth(),
            label={Text("Name")},
            isError= name.isNotEmpty() && !isNameValid
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email=it},
            modifier = Modifier.fillMaxWidth(),
            label={Text("Email")},
            isError= email.isNotEmpty() && !isEmailValid
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password=it},
            modifier = Modifier.fillMaxWidth(),
            label={Text("Password")},
            isError= password.isNotEmpty() && !isPasswordValid,
            visualTransformation =
                if(passwordVisible) VisualTransformation.None
                else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {passwordVisible=!passwordVisible}
                ) {
//                    Icon(
//                        imageVector = if(passwordVisible) Icons.Default.Visibility
//                        else Icons.Default.VisibilityOff,
//                        contentDescription = "Password Visibility",
//                        tint= if (passwordVisible) Color(0xFF3AEF0C)
//                        else Color(0xFFEE1111)
//                    )
                }
            }
        )

        Button(
            onClick = {showDialog = true},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = formValid
        ){
            Text("Submit")
        }
        if(showDialog){
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Submitted Data") },
                text = { Text("Email: $email\nPassword: $password") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("OK Udah selesai")
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun LoginPreview() {
    LoginView()
}

fun isValidName(name: String): Boolean {
    return name.isNotBlank()
}

private fun isValidEmail(email: String): Boolean {
    return email.trim().matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
}

private fun isValidPassword(password: String): Boolean {
    return password.length >= 6
}
}