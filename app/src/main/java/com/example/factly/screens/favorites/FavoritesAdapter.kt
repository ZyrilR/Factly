package com.example.factly.screens.favorites

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.factly.R
import com.example.factly.data.models.Fact

class FavoritesAdapter(
    context: Context,
    private val facts: ArrayList<Fact>,
    private val onItemClick: (Fact) -> Unit,
    private val onItemLongClick: (Int) -> Unit
) : ArrayAdapter<Fact>(context, R.layout.list_item_favorite, facts) {

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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_favorite, parent, false)

        val fact = facts[position]
        val tvTopic   = view.findViewById<TextView>(R.id.textViewTopic)
        val tvContent = view.findViewById<TextView>(R.id.textViewContent)

        tvTopic.text = fact.topic
        tvContent.text = fact.content

        // Apply topic color to badge background
        tvTopic.backgroundTintList = android.content.res.ColorStateList.valueOf(getTopicColor(fact.topic))

        view.setOnClickListener     { onItemClick(fact) }
        view.setOnLongClickListener { onItemLongClick(position); true }

        return view
    }
}