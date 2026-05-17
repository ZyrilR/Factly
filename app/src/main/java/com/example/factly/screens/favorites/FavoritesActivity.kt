package com.example.factly.screens.favorites

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import com.example.factly.R
import com.example.factly.data.models.Fact
import com.example.factly.data.repositories.FactRepository
import com.example.factly.screens.dashboard.DashboardActivity
import com.example.factly.screens.profile.ProfileActivity

class FavoritesActivity : Activity(), FavoritesContract.View {

    private lateinit var presenter: FavoritesPresenter
    private lateinit var adapter: FavoritesAdapter
    private val favorites = ArrayList<Fact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        presenter = FavoritesPresenter(this)

        adapter = FavoritesAdapter(
            this,
            favorites,
            onItemClick     = { fact     -> presenter.onFactClicked(fact) },
            onItemLongClick = { position -> showRemoveConfirmation(position) }
        )

        findViewById<ListView>(R.id.listViewFavorites).adapter = adapter

        findViewById<ImageView>(R.id.imageviewBack).setOnClickListener { finish() }

        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Search filter
        findViewById<EditText>(R.id.editTextSearch).addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().lowercase()
                val filtered = FactRepository.getFavorites().filter {
                    it.content.lowercase().contains(query) || it.topic.lowercase().contains(query)
                }
                favorites.clear()
                favorites.addAll(filtered)
                adapter.notifyDataSetChanged()
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        presenter.loadFavorites()
    }

    private fun showRemoveConfirmation(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Remove Favorite")
            .setMessage("Remove this fact from favorites?")
            .setPositiveButton("Remove") { _, _ -> presenter.removeFavorite(position) }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun showFavorites(facts: List<Fact>) {
        favorites.clear()
        favorites.addAll(facts)
        adapter.notifyDataSetChanged()
    }

    override fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun refreshList() = showFavorites(FactRepository.getFavorites())

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}