package com.example.factly.screens.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.factly.R
import com.example.factly.screens.login.LoginActivity

class RegisterActivity : Activity(), RegisterContract.View {

    private lateinit var presenter: RegisterPresenter
    private lateinit var etFullName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter         = RegisterPresenter(this)
        etFullName        = findViewById(R.id.editTextFullName)
        etUsername        = findViewById(R.id.editTextUsername)
        etEmail           = findViewById(R.id.editTextEmail)
        etPassword        = findViewById(R.id.editTextPassword)
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword)

        findViewById<Button>(R.id.buttonGetStarted).setOnClickListener {
            presenter.onRegisterClicked(
                etUsername.text.toString().trim(),
                etEmail.text.toString().trim(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }

        findViewById<TextView>(R.id.textviewLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        findViewById<android.widget.ImageView>(R.id.imageviewBack).setOnClickListener {
            finish()
        }
    }

    override fun showError(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showSuccess() =
        Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_SHORT).show()

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}