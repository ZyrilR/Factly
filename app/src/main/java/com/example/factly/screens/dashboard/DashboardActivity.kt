package com.example.factly.screens.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.factly.R
import com.example.factly.data.models.Fact
import com.example.factly.screens.favorites.FavoritesActivity
import com.example.factly.screens.login.LoginActivity
import com.example.factly.screens.profile.ProfileActivity

class DashboardActivity : Activity(), DashboardContract.View {

    private lateinit var presenter: DashboardPresenter
    private lateinit var tvFact: TextView
    private lateinit var tvTopic: TextView
    private lateinit var tvWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        presenter = DashboardPresenter(this)
        tvWelcome = findViewById(R.id.textViewTitle)
        tvTopic   = findViewById(R.id.textviewTopic)
        tvFact    = findViewById(R.id.textviewFact)

        val btnNext      = findViewById<Button>(R.id.btnNext)
        val btnSave      = findViewById<Button>(R.id.btnSave)
        val btnFavorites = findViewById<Button>(R.id.btnFavorites)
        val btnProfile   = findViewById<Button>(R.id.buttonProfile)
        val btnLogout    = findViewById<Button>(R.id.buttonLogout)

        btnNext.setOnClickListener      { presenter.onNextClicked() }
        btnSave.setOnClickListener      { presenter.onSaveClicked() }
        btnFavorites.setOnClickListener { presenter.onFavoritesClicked() }
        btnProfile.setOnClickListener   { presenter.onProfileClicked() }
        btnLogout.setOnClickListener    { presenter.onLogoutClicked() }

        presenter.loadFact()
    }

    override fun displayFact(fact: Fact) {
        tvFact.text  = fact.content
        tvTopic.text = "[${fact.topic}]"
    }

    override fun showSaved() {
        Toast.makeText(this, "Saved to Favorites!", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }

    override fun navigateToFavorites() {
        startActivity(Intent(this, FavoritesActivity::class.java))
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