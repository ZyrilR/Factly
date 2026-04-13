package com.example.factly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DashboardActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val welcomeText = findViewById<TextView>(R.id.textViewTitle)
        val profileButton = findViewById<Button>(R.id.buttonProfile)
        val logoutButton = findViewById<Button>(R.id.buttonLogout)

        // Receive username from LoginActivity
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        // Display welcome message
        if (username != null) {
            welcomeText.text = "Welcome, $username!"
        } else {
            welcomeText.text = "Welcome!"
        }

        // Go to Profile
        profileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("email", email)
            intent.putExtra("password", password)

            startActivity(intent)
        }

        // Logout and return to Login screen
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("email", email)
            intent.putExtra("password", password)

            startActivity(intent)
            finish()
        }
    }
}