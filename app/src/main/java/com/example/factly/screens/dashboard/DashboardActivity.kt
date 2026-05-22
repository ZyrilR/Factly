package com.example.factly.screens.dashboard

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.factly.R
import com.example.factly.data.models.Fact
import com.example.factly.data.repositories.FactRepository
import com.example.factly.data.repositories.UserRepository
import com.example.factly.screens.favorites.FavoritesActivity
import com.example.factly.screens.login.LoginActivity
import com.example.factly.screens.profile.ProfileActivity

class DashboardActivity : Activity(), DashboardContract.View {

    private lateinit var presenter: DashboardPresenter
    private lateinit var tvFact: TextView
    private lateinit var tvTopic: TextView
    private lateinit var tvWelcome: TextView
    private lateinit var imageViewStar: ImageView

    private var overlayContainer: FrameLayout? = null
    private var topicSheet: View? = null
    private val pendingSelectedTopics = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        presenter     = DashboardPresenter(this)
        tvWelcome     = findViewById(R.id.textViewTitle)
        tvTopic       = findViewById(R.id.textviewTopic)
        tvFact        = findViewById(R.id.textviewFact)
        imageViewStar = findViewById(R.id.imageViewStar)

        val user = UserRepository.getLoggedInUser()
        if (user != null) tvWelcome.text = "Welcome, ${user.username}!"

        findViewById<LinearLayout>(R.id.btnNext).setOnClickListener {
            presenter.onNextClicked()
        }
        imageViewStar.setOnClickListener {
            presenter.onSaveClicked()
        }
        findViewById<ImageView>(R.id.imageViewMenu).setOnClickListener {
            showTopicOverlay()
        }
        findViewById<LinearLayout>(R.id.navFavorites).setOnClickListener {
            presenter.onFavoritesClicked()
        }
        findViewById<LinearLayout>(R.id.navProfile).setOnClickListener {
            presenter.onProfileClicked()
        }

        presenter.loadFact()
    }

    // ── Topic color map ───────────────────────────────────────────────────────

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

    // ── Topic bottom-sheet overlay ────────────────────────────────────────────

    private fun showTopicOverlay() {
        if (overlayContainer != null) return
        val rootView = window.decorView as ViewGroup

        val dimView = View(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            setBackgroundColor(0x80000000.toInt())
            setOnClickListener { dismissTopicOverlay() }
        }

        val container = FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        container.addView(dimView)

        val sheet = LayoutInflater.from(this)
            .inflate(R.layout.activity_topic_selection, container, false)
        val sheetParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
            Gravity.BOTTOM
        )
        container.addView(sheet, sheetParams)
        rootView.addView(container)

        overlayContainer = container
        topicSheet = sheet

        pendingSelectedTopics.clear()
        pendingSelectedTopics.addAll(FactRepository.selectedTopics)
        buildTopicChips(sheet)

        sheet.post {
            val h = sheet.height.toFloat()
            sheet.translationY = h
            ObjectAnimator.ofFloat(sheet, "translationY", h, 0f).apply {
                duration = 320
                interpolator = DecelerateInterpolator()
                start()
            }
        }

        sheet.findViewById<Button>(R.id.btnApply).setOnClickListener {
            FactRepository.selectedTopics = pendingSelectedTopics.toMutableSet()
            dismissTopicOverlay()
            presenter.loadFact()
        }
        sheet.findViewById<Button>(R.id.btnBack).setOnClickListener {
            dismissTopicOverlay()
        }
    }

    private fun dismissTopicOverlay() {
        val sheet     = topicSheet     ?: return
        val container = overlayContainer ?: return
        val h = sheet.height.toFloat()
        ObjectAnimator.ofFloat(sheet, "translationY", 0f, h).apply {
            duration = 260
            interpolator = AccelerateInterpolator()
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (window.decorView as ViewGroup).removeView(container)
                    overlayContainer = null
                    topicSheet = null
                }
            })
        }
    }

    private fun buildTopicChips(sheet: View) {
        val grid = sheet.findViewById<GridLayout>(R.id.gridTopics)
        grid.removeAllViews()
        val columns = 3
        grid.columnCount = columns

        FactRepository.allTopics.forEachIndexed { index, topic ->
            val chip = TextView(this).apply {
                text    = topic
                gravity = Gravity.CENTER
                setPadding(20, 18, 20, 18)
                textSize = 13f
                layoutParams = GridLayout.LayoutParams().apply {
                    width      = 0
                    columnSpec = GridLayout.spec(index % columns, 1f)
                    rowSpec    = GridLayout.spec(index / columns)
                    setMargins(6, 6, 6, 6)
                }
                updateChipStyle(this, topic in pendingSelectedTopics)
                setOnClickListener {
                    if (topic in pendingSelectedTopics) {
                        pendingSelectedTopics.remove(topic)
                        updateChipStyle(this, false)
                    } else {
                        pendingSelectedTopics.add(topic)
                        updateChipStyle(this, true)
                    }
                }
            }
            grid.addView(chip)
        }
    }

    private fun updateChipStyle(chip: TextView, selected: Boolean) {
        if (selected) {
            chip.setBackgroundResource(R.drawable.chip_selected)
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else {
            chip.setBackgroundResource(R.drawable.chip_unselected)
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }
    }

    // ── DashboardContract.View ────────────────────────────────────────────────

    override fun displayFact(fact: Fact) {
        tvFact.text  = fact.content
        tvTopic.text = fact.topic
        tvTopic.backgroundTintList = ColorStateList.valueOf(getTopicColor(fact.topic))
        // Reflect saved state whenever a fact is displayed
        if (FactRepository.isFavorite(fact)) {
            imageViewStar.setImageResource(R.drawable.ic_star_filled)
        } else {
            imageViewStar.setImageResource(R.drawable.ic_star_outline)
        }
    }

    override fun showSaved() {
        imageViewStar.setImageResource(R.drawable.ic_star_filled)
        Toast.makeText(this, "Saved to Favorites!", Toast.LENGTH_SHORT).show()
    }

    override fun showStarOutline() {
        imageViewStar.setImageResource(R.drawable.ic_star_outline)
        Toast.makeText(this, "Removed from Favorites.", Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun navigateToProfile() =
        startActivity(Intent(this, ProfileActivity::class.java))

    override fun navigateToFavorites() =
        startActivity(Intent(this, FavoritesActivity::class.java))

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}