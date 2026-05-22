package com.example.factly.screens.favorites

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.factly.R
import com.example.factly.data.models.Fact

class FavoritesAdapter(
    private val facts: MutableList<Fact>,
    private val onItemClick: (Fact) -> Unit,
    private val onItemLongClick: (Int) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTopic: TextView   = view.findViewById(R.id.textViewTopic)
        val tvContent: TextView = view.findViewById(R.id.textViewContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = facts[position]
        holder.tvTopic.text   = fact.topic
        holder.tvContent.text = fact.content
        holder.tvTopic.backgroundTintList =
            ColorStateList.valueOf(getTopicColor(fact.topic))

        holder.itemView.setOnClickListener     { onItemClick(fact) }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(holder.adapterPosition)
            true
        }
    }

    override fun getItemCount(): Int = facts.size

    fun submitList(newFacts: List<Fact>) {
        facts.clear()
        facts.addAll(newFacts)
        notifyDataSetChanged()
    }

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