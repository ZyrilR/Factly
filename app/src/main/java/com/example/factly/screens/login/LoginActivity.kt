package com.example.factly.screens.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.factly.R
import com.example.factly.screens.dashboard.DashboardActivity
import com.example.factly.screens.register.RegisterActivity

class LoginActivity : Activity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter  = LoginPresenter(this)
        etUsername = findViewById(R.id.editTextUsername)
        etPassword = findViewById(R.id.editTextPassword)

        val btnLogin          = findViewById<Button>(R.id.buttonLogin)
        val tvCreateAccount   = findViewById<TextView>(R.id.textviewCreateAccount)
        val cbShowPassword    = findViewById<CheckBox>(R.id.checkBoxShowPassword)

        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            etPassword.inputType = if (isChecked) {
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassword.setSelection(etPassword.text.length)
        }

        btnLogin.setOnClickListener {
            presenter.onLoginClicked(
                etUsername.text.toString(),
                etPassword.text.toString()
            )
        }

        tvCreateAccount.setOnClickListener {
            presenter.onRegisterClicked()
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}