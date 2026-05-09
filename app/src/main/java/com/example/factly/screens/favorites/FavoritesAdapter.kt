package com.example.factly.screens.favorites

import android.content.Context
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item_favorite, parent, false)

        val fact = facts[position]

        view.findViewById<TextView>(R.id.textViewTopic).text   = fact.topic
        view.findViewById<TextView>(R.id.textViewContent).text = fact.content

        view.setOnClickListener     { onItemClick(fact) }
        view.setOnLongClickListener { onItemLongClick(position); true }

        return view
    }
}