package com.example.factly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameField = findViewById<EditText>(R.id.editTextUsername)
        val passwordField = findViewById<EditText>(R.id.editTextPassword)
        val loginButton = findViewById<Button>(R.id.buttonLogin)
        val createAccountText = findViewById<TextView>(R.id.textviewCreateAccount)
        val showPasswordCheckBox = findViewById<CheckBox>(R.id.checkBoxShowPassword)

        // Original credentials passed from RegisterActivity
        val originalUsername = intent.getStringExtra("username")
        val originalEmail = intent.getStringExtra("email")
        val originalPassword = intent.getStringExtra("password")

        // Pre-fill fields
        usernameField.setText(originalUsername)
        passwordField.setText(originalPassword)

        // Show/hide password
        showPasswordCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                passwordField.inputType = android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                passwordField.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            passwordField.setSelection(passwordField.text.length)
        }

        // Login button
        loginButton.setOnClickListener {
            val enteredUsername = usernameField.text.toString()
            val enteredPassword = passwordField.text.toString()

            // Validate input
            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            // Check if username matches
            else if (enteredUsername != originalUsername) {
                Toast.makeText(this, "No username found", Toast.LENGTH_SHORT).show()
            }
            // Check if password matches
            else if (enteredPassword != originalPassword) {
                Toast.makeText(this, "Password incorrect", Toast.LENGTH_SHORT).show()
            }
            // Credentials correct, proceed
            else {
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("username", enteredUsername)
                intent.putExtra("email", originalEmail)
                intent.putExtra("password", enteredPassword)
                startActivity(intent)
                finish()
            }
        }

        // Navigate to RegisterActivity
        createAccountText.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}