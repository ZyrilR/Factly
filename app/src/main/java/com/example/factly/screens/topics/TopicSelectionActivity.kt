package com.example.factly.screens.topics

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.factly.R

class TopicSelectionActivity : Activity(), TopicSelectionContract.View {

    private lateinit var presenter: TopicSelectionPresenter
    private lateinit var gridTopics: GridLayout
    private val selectedTopics = mutableSetOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_selection)

        presenter  = TopicSelectionPresenter(this)
        gridTopics = findViewById(R.id.gridTopics)

        findViewById<Button>(R.id.btnApply).setOnClickListener {
            presenter.onApplyClicked(selectedTopics)
        }
        findViewById<Button>(R.id.btnBack).setOnClickListener {
            presenter.onBackClicked()
        }

        presenter.loadTopics()
    }

    override fun showTopics(topics: List<String>, selected: Set<String>) {
        selectedTopics.clear()
        selectedTopics.addAll(selected)
        gridTopics.removeAllViews()

        val columns = 3
        gridTopics.columnCount = columns

        topics.forEachIndexed { index, topic ->
            val chip = TextView(this).apply {
                text    = topic
                gravity = Gravity.CENTER
                setPadding(24, 16, 24, 16)
                textSize = 13f

                val params = GridLayout.LayoutParams().apply {
                    width          = 0
                    columnSpec     = GridLayout.spec(index % columns, 1f)
                    rowSpec        = GridLayout.spec(index / columns)
                    setMargins(8, 8, 8, 8)
                }
                layoutParams = params

                updateChipStyle(this, topic in selectedTopics)

                setOnClickListener {
                    if (topic in selectedTopics) {
                        selectedTopics.remove(topic)
                        updateChipStyle(this, false)
                    } else {
                        selectedTopics.add(topic)
                        updateChipStyle(this, true)
                    }
                }
            }
            gridTopics.addView(chip)
        }
    }

    private fun updateChipStyle(chip: TextView, isSelected: Boolean) {
        if (isSelected) {
            chip.setBackgroundResource(R.drawable.chip_selected)
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else {
            chip.setBackgroundResource(R.drawable.chip_unselected)
            chip.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        }
    }

    override fun navigateBack() {
        finish()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
