package com.example.factly.screens.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.factly.R
import com.example.factly.screens.login.LoginActivity

class RegisterActivity : Activity(), RegisterContract.View {

    private lateinit var presenter: RegisterPresenter
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter         = RegisterPresenter(this)
        etUsername        = findViewById(R.id.editTextUsername)
        etEmail           = findViewById(R.id.editTextEmail)
        etPassword        = findViewById(R.id.editTextPassword)
        etConfirmPassword = findViewById(R.id.editTextConfirmPassword)

        val btnGetStarted  = findViewById<Button>(R.id.buttonGetStarted)
        val cbShowPassword = findViewById<CheckBox>(R.id.checkBoxShowPassword)

        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val inputType = if (isChecked) {
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassword.inputType        = inputType
            etConfirmPassword.inputType = inputType
            etPassword.setSelection(etPassword.text.length)
        }

        btnGetStarted.setOnClickListener {
            presenter.onRegisterClicked(
                etUsername.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                etConfirmPassword.text.toString()
            )
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess() {
        Toast.makeText(this, "Account created! Please log in.", Toast.LENGTH_SHORT).show()
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}