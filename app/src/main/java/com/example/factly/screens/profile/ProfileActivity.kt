package com.example.factly.screens.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.factly.R
import com.example.factly.screens.dashboard.DashboardActivity
import com.example.factly.screens.favorites.FavoritesActivity
import com.example.factly.screens.login.LoginActivity

class ProfileActivity : Activity(), ProfileContract.View {

    private lateinit var presenter: ProfilePresenter
    private lateinit var etFullName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvDisplayName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter     = ProfilePresenter(this)
        etFullName    = findViewById(R.id.editTextFullName)
        etEmail       = findViewById(R.id.editTextEmail)
        etPassword    = findViewById(R.id.editTextPassword)
        tvDisplayName = findViewById(R.id.textviewDisplayName)

        findViewById<Button>(R.id.buttonProfile).setOnClickListener {
            presenter.onBackClicked()
        }

        findViewById<Button>(R.id.buttonLogout).setOnClickListener {
            presenter.onLogoutClicked()
        }

        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            presenter.onBackClicked()
        }

        findViewById<LinearLayout>(R.id.navFavorites).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }

        presenter.loadUserInfo()
    }

    override fun showUserInfo(username: String, email: String, password: String) {
        tvDisplayName.text = username
        etFullName.setText(username)
        etEmail.setText(email)
        etPassword.setText(password)
    }

    override fun showError(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun showSuccess(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

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