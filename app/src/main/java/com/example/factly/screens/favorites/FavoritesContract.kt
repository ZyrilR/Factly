package com.example.factly.screens.favorites

import com.example.factly.data.models.Fact

interface FavoritesContract {

    interface View {
        fun showFavorites(facts: List<Fact>)
        fun showMessage(message: String)
        fun refreshList()
    }

    interface Presenter {
        fun loadFavorites()
        fun onFactClicked(fact: Fact)
        fun removeFavorite(position: Int)
        fun onDestroy()
    }
}