package com.example.factly.screens.favorites

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.factly.R
import com.example.factly.data.models.Fact
import com.example.factly.data.repositories.FactRepository
import com.example.factly.screens.dashboard.DashboardActivity
import com.example.factly.screens.profile.ProfileActivity

class FavoritesActivity : Activity(), FavoritesContract.View {

    private lateinit var presenter: FavoritesPresenter
    private lateinit var adapter: FavoritesAdapter
    private val favorites = mutableListOf<Fact>()

    private var detailOverlay: View? = null
    private var confirmOverlay: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        presenter = FavoritesPresenter(this)

        adapter = FavoritesAdapter(
            facts           = favorites,
            onItemClick     = { fact     -> presenter.onFactClicked(fact) },
            onItemLongClick = { position -> presenter.onRemoveClicked(position) }
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter       = adapter

        // Swipe-to-delete → custom confirmation
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(rv: RecyclerView, vh: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                // Snap item back visually; let the confirmation overlay handle removal
                adapter.notifyItemChanged(position)
                presenter.onRemoveClicked(position)
            }
        }).attachToRecyclerView(recyclerView)

        // Nav
        findViewById<ImageView>(R.id.imageviewBack).setOnClickListener { finish() }
        findViewById<LinearLayout>(R.id.navHome).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Search filter
        findViewById<EditText>(R.id.editTextSearch)
            .addTextChangedListener(object : android.text.TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val query = s.toString().lowercase()
                    val filtered = FactRepository.getFavorites().filter {
                        it.content.lowercase().contains(query) ||
                                it.topic.lowercase().contains(query)
                    }
                    adapter.submitList(filtered)
                }
                override fun afterTextChanged(s: android.text.Editable?) {}
            })

        presenter.loadFavorites()
    }

    // ── FavoritesContract.View ────────────────────────────────────────────────

    override fun showFavorites(facts: List<Fact>) {
        adapter.submitList(facts)
    }

    override fun showRemoveConfirmation(position: Int) {
        dismissConfirmOverlay()

        val rootView = window.decorView as ViewGroup
        val overlay  = LayoutInflater.from(this)
            .inflate(R.layout.dialog_remove_confirmation, rootView, false)

        overlay.setOnClickListener { dismissConfirmOverlay() }
        overlay.findViewById<View>(R.id.cardRemoveConfirm).setOnClickListener { /* consume */ }

        overlay.findViewById<Button>(R.id.buttonConfirmRemove).setOnClickListener {
            dismissConfirmOverlay()
            presenter.removeFavorite(position)
        }
        overlay.findViewById<Button>(R.id.buttonCancelRemove).setOnClickListener {
            dismissConfirmOverlay()
        }

        rootView.addView(overlay)
        confirmOverlay = overlay
    }

    private fun dismissConfirmOverlay() {
        confirmOverlay?.let {
            (window.decorView as ViewGroup).removeView(it)
            confirmOverlay = null
        }
    }

    override fun showFactDetail(fact: Fact) {
        dismissDetailOverlay()

        val rootView = window.decorView as ViewGroup
        val overlay  = LayoutInflater.from(this)
            .inflate(R.layout.dialog_fact_detail, rootView, false)

        overlay.findViewById<TextView>(R.id.textViewDetailTopic).apply {
            text = fact.topic
            backgroundTintList = ColorStateList.valueOf(getTopicColor(fact.topic))
        }
        overlay.findViewById<TextView>(R.id.textViewDetailFact).text     = fact.content
        overlay.findViewById<TextView>(R.id.textViewDetailExpanded).text =
            fact.expandedInfo.ifBlank { "No additional information available." }

        // Star in detail — starts filled; click unfavorites and shows confirmation
        val detailStar = overlay.findViewById<ImageView>(R.id.imageViewDetailStar)
        detailStar.setImageResource(R.drawable.ic_star_filled)
        detailStar.setOnClickListener {
            // Find position of this fact in the current list
            val position = favorites.indexOf(fact)
            if (position >= 0) {
                detailStar.setImageResource(R.drawable.ic_star_outline)
                dismissDetailOverlay()
                presenter.onRemoveClicked(position)
            }
        }

        overlay.setOnClickListener { dismissDetailOverlay() }
        overlay.findViewById<View>(R.id.cardFactDetail).setOnClickListener { /* consume */ }
        overlay.findViewById<Button>(R.id.buttonCloseDetail).setOnClickListener { dismissDetailOverlay() }

        rootView.addView(overlay)
        detailOverlay = overlay
    }

    private fun dismissDetailOverlay() {
        detailOverlay?.let {
            (window.decorView as ViewGroup).removeView(it)
            detailOverlay = null
        }
    }

    override fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun refreshList() = showFavorites(FactRepository.getFavorites())

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private fun getTopicColor(topic: String): Int = Color.parseColor(
        when (topic) {
            "Science"    -> "#4FC3F7"
            "Sports"     -> "#81C784"
            "Geography"  -> "#FF8A65"
            "Technology" -> "#9575CD"
            "Food"       -> "#FFD54F"
            "Animals"    -> "#A5D6A7"
            "Space"      -> "#7986CB"
            "Human Body" -> "#F06292"
            "History"    -> "#8D6E63"
            else         -> "#BDBDBD"
        }
    )
}