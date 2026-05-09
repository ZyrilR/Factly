package com.example.factly.screens.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.factly.R
import com.example.factly.screens.dashboard.DashboardActivity
import com.example.factly.screens.login.LoginActivity

class ProfileActivity : Activity(), ProfileContract.View {

    private lateinit var presenter: ProfilePresenter
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter  = ProfilePresenter(this)
        etUsername = findViewById(R.id.editTextUsername)
        etEmail    = findViewById(R.id.editTextEmail)
        etPassword = findViewById(R.id.editTextPassword)

        val btnBack        = findViewById<Button>(R.id.buttonProfile)
        val cbShowPassword = findViewById<CheckBox>(R.id.checkBoxShowPassword)

        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            etPassword.inputType = if (isChecked) {
                android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassword.setSelection(etPassword.text.length)
        }

        btnBack.setOnClickListener { presenter.onBackClicked() }

        presenter.loadUserInfo()
    }

    override fun showUserInfo(username: String, email: String, password: String) {
        etUsername.setText(username)
        etEmail.setText(email)
        etPassword.setText(password)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToDashboard() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
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